package stripe;

import java.util.*;

public class LoadBalancer {

    public List<String> routeRequest(int numTargets, int maxConnectionsPerTarget, List<String> requests) {
        int n = numTargets;
        int cap = maxConnectionsPerTarget;

        // 状态存储
        int[] cnt = new int[n]; // 各 Target 的当前连接数
        Map<String, ConnectionInfo> con = new HashMap<>(); // cid -> {target, uid, oid}
        List<Set<String>> perT = new ArrayList<>(n); // 每个 Target 拥有的 cid 集合
        for (int i = 0; i < n; i++) perT.add(new HashSet<>());

        Map<String, Integer> objToT = new HashMap<>(); // oid -> target
        Map<String, Integer> objUse = new HashMap<>(); // oid -> 使用该对象的连接数
        Set<Integer> disabled = new HashSet<>();
        List<String> logs = new ArrayList<>();

        // 用于排序的连接顺序
        Map<String, Integer> seenOrder = new HashMap<>();
        int[] orderSeq = {0}; // 使用数组以便在匿名逻辑或闭包中修改（虽然此例不需要）

        // --- 内部辅助函数 ---

        // 选择负载最小的 Target
        java.util.function.Supplier<Integer> pickTarget = () -> {
            Integer bestT = null;
            int bestLoad = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if (disabled.contains(i)) continue;
                if (cnt[i] >= cap) continue;

                if (bestT == null || cnt[i] < bestLoad || (cnt[i] == bestLoad && i < bestT)) {
                    bestT = i;
                    bestLoad = cnt[i];
                }
            }
            return bestT;
        };

        // 处理连接逻辑
        java.util.function.Function<String[], Boolean> attach = (parts) -> {
            String cid = parts[1];
            String uid = parts[2];
            String oid = parts[3];

            int t;
            if (objToT.containsKey(oid)) {
                t = objToT.get(oid);
                // 如果对象所在的 Target 已禁用或满载，连接失败
                if (disabled.contains(t) || cnt[t] >= cap) return false;
            } else {
                Integer picked = pickTarget.get();
                if (picked == null) return false;
                t = picked;
            }

            // 更新状态
            con.put(cid, new ConnectionInfo(t, uid, oid));
            perT.get(t).add(cid);
            cnt[t]++;
            objUse.put(oid, objUse.getOrDefault(oid, 0) + 1);
            objToT.put(oid, t);

            logs.add(cid + "," + uid + "," + (t + 1));
            return true;
        };

        // 处理断开连接逻辑
        java.util.function.Consumer<String> detach = (cid) -> {
            ConnectionInfo info = con.remove(cid);
            if (info == null) return;

            int t = info.target;
            String oid = info.oid;

            perT.get(t).remove(cid);
            cnt[t]--;

            int currentUse = objUse.getOrDefault(oid, 0) - 1;
            if (currentUse <= 0) {
                objUse.remove(oid);
                objToT.remove(oid);
            } else {
                objUse.put(oid, currentUse);
            }
        };

        // --- 主循环 ---
        for (String line : requests) {
            String[] parts = line.split(",");
            for (int i = 0; i < parts.length; i++) parts[i] = parts[i].trim();
            String op = parts[0];

            if (op.equals("CONNECT")) {
                String cid = parts[1];
                if (!seenOrder.containsKey(cid)) {
                    seenOrder.put(cid, ++orderSeq[0]);
                }
                attach.apply(parts);

            } else if (op.equals("DISCONNECT")) {
                String cid = parts[1];
                if (con.containsKey(cid)) {
                    detach.accept(cid);
                }

            } else if (op.equals("DISABLE")) {
                int t = Integer.parseInt(parts[1]) - 1;
                disabled.add(t);

                // 筛选并排序需要迁移的连接
                List<String> evictList = new ArrayList<>(perT.get(t));
                evictList.sort((a, b) -> {
                    int orderA = seenOrder.getOrDefault(a, Integer.MAX_VALUE);
                    int orderB = seenOrder.getOrDefault(b, Integer.MAX_VALUE);
                    if (orderA != orderB) return Integer.compare(orderA, orderB);
                    return a.compareTo(b);
                });

                // 暂存连接信息
                List<String[]> triples = new ArrayList<>();
                for (String cid : evictList) {
                    ConnectionInfo info = con.get(cid);
                    triples.add(new String[]{"CONNECT", cid, info.uid, info.oid});
                }

                // 断开旧连接
                for (String cid : evictList) {
                    detach.accept(cid);
                }
                perT.get(t).clear();

                // 尝试重新挂载到其他 Target
                for (String[] tri : triples) {
                    attach.apply(tri);
                }

                disabled.remove(t);
            }
        }

        return logs;
    }

    // 内部类用于存储连接信息
    private static class ConnectionInfo {
        int target;
        String uid, oid;
        ConnectionInfo(int target, String uid, String oid) {
            this.target = target;
            this.uid = uid;
            this.oid = oid;
        }
    }
}