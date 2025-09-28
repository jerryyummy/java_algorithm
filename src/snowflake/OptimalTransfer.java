package snowflake;

import java.util.*;

public class OptimalTransfer {

    public static List<Integer> getMinCost(List<Integer> capacity, List<Integer> fromServer, List<Integer> toServer) {
        int n = capacity.size();
        int[] cap = new int[n];
        for (int i = 0; i < n; i++) {
            cap[i] = capacity.get(i);
        }

        // 为每个服务器找到最近的邻居
        int[] c = new int[n];
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                c[i] = 1;  // 第一个服务器的最近邻居是下一个
            } else if (i == n - 1) {
                c[i] = n - 2;  // 最后一个服务器的最近邻居是前一个
            } else {
                int l = cap[i] - cap[i - 1];  // 到左邻居的距离
                int r = cap[i + 1] - cap[i];  // 到右邻居的距离
                c[i] = (l < r) ? i - 1 : i + 1;  // 选择距离更近的邻居
            }
        }

        // 计算前向前缀和（从左到右通过最优路径的累积成本）
        int[] pFF = new int[n];
        for (int i = 0; i < n - 1; i++) {
            int w;
            if (c[i] == i + 1) {
                // 如果最近邻居是右邻居，使用最近邻居连接，成本为1
                w = 1;
            } else {
                // 否则直接移动到右邻居，成本为容量差
                w = cap[i + 1] - cap[i];
            }
            pFF[i + 1] = pFF[i] + w;
        }

        // 计算后向前缀和（从右到左通过最优路径的累积成本）
        int[] pFB = new int[n];
        for (int i = 1; i < n; i++) {
            int w;
            if (c[i] == i - 1) {
                // 如果最近邻居是左邻居，使用最近邻居连接，成本为1
                w = 1;
            } else {
                // 否则直接移动到左邻居，成本为容量差
                w = cap[i] - cap[i - 1];
            }
            pFB[i] = pFB[i - 1] + w;
        }

        // 处理查询
        List<Integer> ans = new ArrayList<>();
        for (int q = 0; q < fromServer.size(); q++) {
            int u = fromServer.get(q);
            int v = toServer.get(q);
            int cost;

            if (u < v) {
                // 从左到右：使用前向路径
                cost = pFF[v] - pFF[u];
            } else {
                // 从右到左：使用后向路径
                cost = pFB[u] - pFB[v];
            }

            // 直接连接的成本
            int directCost = Math.abs(cap[u] - cap[v]);

            // 选择最小成本
            ans.add(Math.min(cost, directCost));
        }

        return ans;
    }

    // 测试方法
    public static void main(String[] args) {
        // 根据题目示例进行测试
        List<Integer> capacity = Arrays.asList(2, 7, 10);
        List<Integer> fromServer = Arrays.asList(0, 1, 2);
        List<Integer> toServer = Arrays.asList(2, 2, 1);

        System.out.println("服务器容量: " + capacity);

        // 手动验证最近邻居计算
        System.out.println("\n最近邻居验证:");
        System.out.println("服务器0: 只能选择服务器1");
        System.out.println("服务器1: 左邻居距离=" + (7-2) + ", 右邻居距离=" + (10-7) + ", 选择右邻居2");
        System.out.println("服务器2: 只能选择服务器1");

        List<Integer> result = getMinCost(capacity, fromServer, toServer);

        System.out.println("\n查询结果:");
        for (int i = 0; i < result.size(); i++) {
            System.out.printf("从服务器 %d 到服务器 %d 的最小成本: %d%n",
                    fromServer.get(i), toServer.get(i), result.get(i));
        }

        System.out.println("\n详细分析:");
        System.out.println("0->2: 通过路径0->1->2，成本1+1=2，直接成本|2-10|=8，选择min(2,8)=2");
        System.out.println("1->2: 1的最近邻居就是2，成本1，直接成本|7-10|=3，选择min(1,3)=1");
        System.out.println("2->1: 2的最近邻居就是1，成本1，直接成本|10-7|=3，选择min(1,3)=1");

        // 预期输出: [2, 1, 1]
        System.out.println("\n结果数组: " + result);
    }

}