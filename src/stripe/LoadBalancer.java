package stripe;

import java.util.*;

public class LoadBalancer {

    // 内部类：存储活跃连接的元数据
    static class Connection {
        String connId;
        String userId;
        String objectId;
        int targetId; // 1-based index

        public Connection(String connId, String userId, String objectId, int targetId) {
            this.connId = connId;
            this.userId = userId;
            this.objectId = objectId;
            this.targetId = targetId;
        }
    }

    // 全局状态变量
    private int[] serverLoad;               // 记录每个服务器当前的连接数 (index 1 to numTargets)
    private boolean[] isServerDown;         // 记录服务器是否处于 SHUTDOWN 过程中的不可用状态

    // 核心映射
    private Map<String, Integer> objectToTargetMap;    // Object Pinning: objectId -> targetId
    private Map<String, Integer> objectRefCount;       // 引用计数: objectId -> active connection count
    private Map<String, Connection> activeConnections; // 快速查找: connId -> Connection Object

    private List<String> resultLog;         // 输出日志
    private int maxConnectionsPerTarget;
    private int numTargets;

    public List<String> route_requests(int numTargets, int maxConnectionsPerTarget, List<String> requests) {
        // 1. 初始化数据结构
        this.numTargets = numTargets;
        this.maxConnectionsPerTarget = maxConnectionsPerTarget;
        this.serverLoad = new int[numTargets + 1];
        this.isServerDown = new boolean[numTargets + 1];

        this.objectToTargetMap = new HashMap<>();
        this.objectRefCount = new HashMap<>();
        this.activeConnections = new HashMap<>();
        this.resultLog = new ArrayList<>();

        // 2. 遍历处理请求
        for (String req : requests) {
            String[] parts = req.split(",");
            String action = parts[0];

            if (action.equals("CONNECT")) {
                String connId = parts[1];
                String userId = parts[2];
                String objectId = parts[3];
                // 尝试建立连接
                attemptConnect(connId, userId, objectId);

            } else if (action.equals("DISCONNECT")) {
                // 题目示例: DISCONNECT,conn1,userA,obj1 (我们主要需要 connId)
                String connId = parts[1];
                performDisconnect(connId);

            } else if (action.equals("SHUTDOWN")) {
                int targetId = Integer.parseInt(parts[1]);
                performShutdown(targetId);
            }
        }

        return resultLog;
    }

    /**
     * 尝试分配服务器并建立连接
     * 逻辑顺序: Object Pinning -> Capacity Check -> Least Connection Logic
     */
    private void attemptConnect(String connId, String userId, String objectId) {
        int assignedTarget = -1;

        // --- Step A: 检查 Object Pinning (Part 3) ---
        if (objectToTargetMap.containsKey(objectId)) {
            int pinnedTarget = objectToTargetMap.get(objectId);

            // 检查被 Pin 的服务器是否可用 (Part 4 & 5)
            // 如果服务器 Down 或者 满了，连接必须被拒绝 (Rejected)
            // 注意：不能因为满了就去别的服务器，必须严格遵守 Pinning
            if (!isServerDown[pinnedTarget] && serverLoad[pinnedTarget] < maxConnectionsPerTarget) {
                assignedTarget = pinnedTarget;
            } else {
                // Reject connection (Do nothing, do not log)
                return;
            }
        }
        // --- Step B: 寻找最佳服务器 (Part 1 & 4) ---
        else {
            int bestTarget = -1;
            int minLoad = Integer.MAX_VALUE;

            for (int i = 1; i <= numTargets; i++) {
                // 1. 跳过正在重启的服务器
                if (isServerDown[i]) continue;

                // 2. 跳过已满的服务器
                if (serverLoad[i] >= maxConnectionsPerTarget) continue;

                // 3. 找负载最小的
                if (serverLoad[i] < minLoad) {
                    minLoad = serverLoad[i];
                    bestTarget = i;
                }
                // 4. Tie-breaker: 选 index 小的 (隐含在循环顺序中，不用额外写)
            }
            assignedTarget = bestTarget;
        }

        // --- Step C: 如果成功分配，更新状态 ---
        if (assignedTarget != -1) {
            // 更新负载
            serverLoad[assignedTarget]++;

            // 更新 Pinning 和 引用计数
            objectToTargetMap.put(objectId, assignedTarget);
            objectRefCount.put(objectId, objectRefCount.getOrDefault(objectId, 0) + 1);

            // 记录连接
            Connection conn = new Connection(connId, userId, objectId, assignedTarget);
            activeConnections.put(connId, conn);

            // 写入日志
            resultLog.add(String.format("%s,%s,%d", connId, userId, assignedTarget));
        }
    }

    /**
     * 执行断开连接
     * 逻辑: 减少负载 -> 减少引用计数 -> (如果计数为0) 移除 Pin
     */
    private void performDisconnect(String connId) {
        Connection conn = activeConnections.get(connId);
        if (conn == null) return; // 防御性编程

        // 1. 释放服务器资源
        if (conn.targetId >= 1 && conn.targetId <= numTargets) {
            // 即使服务器 Down 了，负载计数也要减，因为连接确实断了
            if (serverLoad[conn.targetId] > 0) {
                serverLoad[conn.targetId]--;
            }
        }

        // 2. 更新 Object Pinning 引用计数
        // 只有当该 Object 的 *最后一个* 连接断开时，Pin 才失效
        String objId = conn.objectId;
        int count = objectRefCount.getOrDefault(objId, 0);

        if (count <= 1) {
            objectRefCount.remove(objId);
            objectToTargetMap.remove(objId); // Unpin
        } else {
            objectRefCount.put(objId, count - 1);
        }

        // 3. 移除连接记录
        activeConnections.remove(connId);
    }

    /**
     * 执行服务器关闭和重路由 (Part 5)
     * 逻辑: 找出受害者 -> 彻底清除状态 -> 标记服务器Down -> 排序 -> 重新连接 -> 标记服务器Up
     */
    private void performShutdown(int targetId) {
        // 1. 找出所有位于该服务器的连接 (Victims)
        List<Connection> victims = new ArrayList<>();
        for (Connection conn : activeConnections.values()) {
            if (conn.targetId == targetId) {
                victims.add(conn);
            }
        }

        // 2. 彻底清除这些连接的状态 (Evict)
        // 这一步非常重要：必须调用 performDisconnect 来清除 objectToTargetMap
        // 否则重连时，系统会以为 object 还在旧服务器上
        for (Connection conn : victims) {
            performDisconnect(conn.connId);
        }

        // 3. 标记服务器暂时不可用
        isServerDown[targetId] = true;

        // 4. 准备重路由：按 connection ID 排序
        // 假设是字符串字典序 (conn1, conn10, conn2...)
        victims.sort(Comparator.comparing(c -> c.connId));

        // 5. 重新路由 (Reroute)
        for (Connection conn : victims) {
            // 调用通用的 connect 方法，它会自动处理 Load Balancing, Pinning 和 Logging
            attemptConnect(conn.connId, conn.userId, conn.objectId);
        }

        // 6. 服务器恢复可用 (Immediately restarts)
        isServerDown[targetId] = false;
    }

    // ==========================================
    // Main 测试入口
    // ==========================================
    public static void main(String[] args) {
        LoadBalancer solver = new LoadBalancer();

        System.out.println("--- Test Case 1: Basic & Pinning ---");
        List<String> reqs1 = Arrays.asList(
                "CONNECT,conn1,userA,obj1", // Should go to 1 (Load 0)
                "CONNECT,conn2,userB,obj2", // Should go to 2 (Load 0)
                "CONNECT,conn3,userC,obj1"  // Should go to 1 (Pinned to obj1)
        );
        System.out.println(solver.route_requests(2, 5, reqs1));
        // Expected: [conn1,userA,1, conn2,userB,2, conn3,userC,1]

        System.out.println("\n--- Test Case 2: Capacity Rejection ---");
        solver = new LoadBalancer(); // Reset
        List<String> reqs2 = Arrays.asList(
                "CONNECT,conn1,userA,obj1", // To 1
                "CONNECT,conn2,userB,obj2", // To 2
                "CONNECT,conn3,userC,obj3"  // All full (Cap=1), Rejected
        );
        System.out.println(solver.route_requests(2, 1, reqs2));
        // Expected: [conn1,userA,1, conn2,userB,2] (conn3 missing)

        System.out.println("\n--- Test Case 3: Shutdown & Reroute ---");
        solver = new LoadBalancer(); // Reset
        List<String> reqs3 = Arrays.asList(
                "CONNECT,conn1,userA,obj1", // To 1
                "CONNECT,conn2,userB,obj2", // To 2
                "SHUTDOWN,1",               // Evict conn1. Target 1 down.
                // Reroute conn1 -> Target 2 (since 1 is down)
                "CONNECT,conn3,userC,obj1"  // Should follow obj1 to Target 2
        );
        // Note: When conn1 is rerouted to 2, obj1 becomes pinned to 2.
        System.out.println(solver.route_requests(2, 5, reqs3));
        // Expected:
        // conn1,userA,1
        // conn2,userB,2
        // conn1,userA,2 (Rerouted log)
        // conn3,userC,2 (Follows pin)

        System.out.println("\n--- Test Case 4: Capacity Rejection ---");
        solver = new LoadBalancer(); // Reset
        List<String> reqs4 = Arrays.asList(
                "CONNECT,conn1,userA,obj1",
                "DISCONNECT,conn1,userA,obj1",
                "CONNECT,conn2,userB,obj2",
                "CONNECT,conn3,userA,obj1"
        );
        System.out.println(solver.route_requests(2, 10, reqs4));
    }
}