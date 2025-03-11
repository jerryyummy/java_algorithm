import java.util.*;

public class Main {
    static List<Integer>[] tree;
    static char[] letters;
    static int[][] parent;
    static int[] depth;
    static int n, LOG;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int q = sc.nextInt();

        LOG = (int) (Math.log(n) / Math.log(2)) + 1;
        tree = new ArrayList[n + 1];
        parent = new int[n + 1][LOG];
        depth = new int[n + 1];
        letters = new char[n + 1];

        for (int i = 1; i <= n; i++) tree[i] = new ArrayList<>();

        for (int i = 2; i <= n; i++) {
            int p = sc.nextInt();
            tree[p] = new ArrayList<>();
            tree[p].add(i);
            parent[i][0] = p;
        }

        String s = sc.next();
        for (int i = 1; i <= n; i++) letters[i] = s.charAt(i - 1);

        // 预处理深度和倍增 LCA
        dfs(1, 0);
        preprocessLCA();

        // 处理查询
        for (int i = 0; i < q; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int lca = getLCA(u, v);
            String path = getPathString(u, lca) + getPathString(v, lca).reverse().toString();
            if (containsBUG(path)) {
                System.out.println("NO");
            } else {
                System.out.println("YES");
            }
        }
        sc.close();
    }

    // 预处理深度和 2^i 祖先
    static void dfs(int node, int par) {
        parent[node][0] = par;
        depth[node] = depth[par] + 1;
        for (int child : tree[node]) {
            if (child != par) dfs(child, node);
        }
    }

    // 预处理倍增 LCA 表
    static void preprocessLCA() {
        for (int j = 1; j < LOG; j++) {
            for (int i = 1; i <= n; i++) {
                if (parent[i][j - 1] != 0) {
                    parent[i][j] = parent[parent[i][j - 1]][j - 1];
                }
            }
        }
    }

    // 获取 LCA
    static int getLCA(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        int diff = depth[u] - depth[v];
        for (int j = 0; j < LOG; j++) {
            if ((diff & (1 << j)) != 0) u = parent[u][j];
        }
        if (u == v) return u;
        for (int j = LOG - 1; j >= 0; j--) {
            if (parent[u][j] != parent[v][j]) {
                u = parent[u][j];
                v = parent[v][j];
            }
        }
        return parent[u][0];
    }

    // 获取 u 到 LCA 的路径字符串
    static StringBuilder getPathString(int u, int lca) {
        StringBuilder path = new StringBuilder();
        while (u != lca) {
            path.append(letters[u]);
            u = parent[u][0];
        }
        return path;
    }

    // 判断目标字符串 "BUG" 是否是 path 的子序列
    static boolean containsBUG(String path) {
        String target = "BUG";
        int ti = 0, pi = 0;
        while (ti < target.length() && pi < path.length()) {
            if (target.charAt(ti) == path.charAt(pi)) {
                ti++;
            }
            pi++;
        }
        return ti == target.length();
    }
}