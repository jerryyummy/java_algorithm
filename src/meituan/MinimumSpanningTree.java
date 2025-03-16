package meituan;

import java.util.*;

public class MinimumSpanningTree {

    // Union-Find 并查集
    static class UnionFind {
        private int[] parent, rank;

        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // 路径压缩
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                if (rank[rootX] > rank[rootY]) {
                    parent[rootY] = rootX;
                } else if (rank[rootX] < rank[rootY]) {
                    parent[rootX] = rootY;
                } else {
                    parent[rootY] = rootX;
                    rank[rootX]++;
                }
            }
        }
    }

    // 扩展欧几里得算法 (ExGCD) 计算 ax + by = gcd(a, b)
    static class GCDResult {
        long gcd, x, y;
        public GCDResult(long gcd, long x, long y) {
            this.gcd = gcd;
            this.x = x;
            this.y = y;
        }
    }

    public static GCDResult exgcd(long a, long b) {
        if (b == 0) {
            return new GCDResult(a, 1, 0);
        }
        GCDResult res = exgcd(b, a % b);
        long x = res.y;
        long y = res.x - (a / b) * res.y;
        return new GCDResult(res.gcd, x, y);
    }

    // 计算满足 `ax + by = k` 的解的个数
    public static long calc(long a, long b, long k) {
        GCDResult res = exgcd(a, b);
        long g = res.gcd, x = res.x, y = res.y;

        if (k % g != 0) {
            return 0; // 无解
        }

        long m = k / g;
        x *= m;
        y *= m;
        long dx = b / g, dy = a / g;

        if (x <= 0) {
            long c = (-x) / dx + 1;
            x += c * dx;
            y -= c * dy;
        }
        if (y <= 0) {
            long c = (-y) / dy + 1;
            y += c * dy;
            x -= c * dx;
        }
        if (Math.min(x, y) <= 0) {
            return 0;
        }

        x = (x - 1) / dx;
        y = (y - 1) / dy;
        return x + y + 1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long k = scanner.nextLong();
        long[] arr = new long[n];

        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextLong();
        }
        scanner.close();

        List<long[]> edges = new ArrayList<>();

        // 计算所有可能的边权重
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                long w = calc(arr[i], arr[j], k);
                if (w > 0) {
                    edges.add(new long[]{w, i, j});
                }
            }
        }

        // 按照边权进行排序 (Kruskal算法)
        edges.sort(Comparator.comparingLong(a -> a[0]));

        // 并查集初始化
        UnionFind uf = new UnionFind(n);
        long mstWeight = 0;
        int mstEdges = 0;

        // Kruskal 最小生成树算法
        for (long[] edge : edges) {
            long w = edge[0];
            int u = (int) edge[1];
            int v = (int) edge[2];

            if (uf.find(u) != uf.find(v)) {
                uf.union(u, v);
                mstWeight += w;
                mstEdges++;

                if (mstEdges == n - 1) {
                    break;
                }
            }
        }

        // 输出最小生成树的权重
        System.out.println(mstWeight);
    }
}
