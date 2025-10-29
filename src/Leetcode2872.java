import java.util.*;

public class Leetcode2872 {
    public int maxKDivisibleComponents(int n, int[][] edges, int[] values, int k) {
        List<Integer>[] g = new ArrayList[n];
        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            g[u].add(v); g[v].add(u);
        }

        // 建 parent 与遍历顺序（从根0出发）
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        int[] order = new int[n];
        int top = 0;
        order[top++] = 0;
        parent[0] = 0;
        for (int i = 0; i < top; i++) {
            int u = order[i];
            for (int v : g[u]) if (parent[v] == -1) {
                parent[v] = u;
                order[top++] = v;
            }
        }

        int res = 0;
        int[] remain = new int[n]; // 子树和对 k 的余数

        // 按后序（从叶到根）聚合
        for (int i = n - 1; i >= 0; i--) {
            int v = order[i];
            int sum = ((values[v] % k) + k) % k; // 防负数

            for (int j : g[v]) {
                if (j == parent[v]) continue;   // 跳过父亲
                sum = (sum + remain[j]) % k;    // 累加子树余数
            }

            if (sum == 0) {
                res++;           // 这个子树可以切成一个独立组件
                remain[v] = 0;   // 向上不再贡献
            } else {
                remain[v] = sum; // 把余数贡献给父亲
            }
        }

        return res;
    }
}
