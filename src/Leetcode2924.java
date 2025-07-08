public class Leetcode2924 {
    public int findChampion(int n, int[][] edges) {
        UnionFind unionFind = new UnionFind(n);
        for (int[] edge : edges) {
            unionFind.union(edge[0], edge[1]);
        }

        int res = unionFind.find(0);
        for (int i = 1; i < n; i++) {
            int des = unionFind.find(i);
            if (des != res) {
                return -1;
            }
        }
        return res;
    }

    private static class UnionFind {
        private int[] parent;
        public UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) {
                return;
            }else {
                parent[q] = p;
            }
        }

        public int find(int p) {
            if (parent[p] != p) {
                parent[p] = find(parent[p]);
            }
            return parent[p];
        }
    }
}
