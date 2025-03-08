package mihoyo;

import java.util.*;

public class HammingDistance {
    static int n, m, k;
    static String[] strings;
    static boolean[][] graph;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt(); // 读取测试组数

        while (T-- > 0) {
            n = scanner.nextInt();
            m = scanner.nextInt();
            k = scanner.nextInt();
            scanner.nextLine();

            strings = new String[n];
            for (int i = 0; i < n; i++) {
                strings[i] = scanner.nextLine();
            }

            // 构建邻接矩阵，记录字符串之间的可达性
            graph = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (getDifference(strings[i], strings[j]) <= k) {
                        graph[i][j] = graph[j][i] = true;
                    }
                }
            }

            // 计算最大连通分量大小
            visited = new boolean[n];
            int maxComponentSize = 0;
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    maxComponentSize = Math.max(maxComponentSize, dfs(i));
                }
            }

            // 输出结果
            if (maxComponentSize == n) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
                System.out.println(n - maxComponentSize); // 需要删除的字符串数量
            }
        }

        scanner.close();
    }

    // **计算两个字符串的差异值（Hamming Distance）**
    private static int getDifference(String s1, String s2) {
        int diff = 0;
        for (int i = 0; i < m; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diff++;
            }
        }
        return diff;
    }

    // **DFS 计算连通分量的大小**
    private static int dfs(int node) {
        Stack<Integer> stack = new Stack<>();
        stack.push(node);
        visited[node] = true;
        int size = 1;

        while (!stack.isEmpty()) {
            int curr = stack.pop();
            for (int i = 0; i < n; i++) {
                if (!visited[i] && graph[curr][i]) {
                    visited[i] = true;
                    stack.push(i);
                    size++;
                }
            }
        }
        return size;
    }
}
