public class Leetcode959 {
    public int regionsBySlashes(String[] grid) {
        int res = 0;
        int[][] g = new int[grid.length * 3][grid[0].length() * 3];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length(); j++) {
                if (grid[i].charAt(j) == '/')
                    g[i * 3 + 2][j * 3] = g[i * 3 + 1][j * 3 + 1] = g[i * 3][j * 3 + 2] = 1;
                else if (grid[i].charAt(j) == '\\')
                    g[i * 3][j * 3] = g[i * 3 + 1][j * 3 + 1] = g[i * 3 + 2][j * 3 + 2] = 1;
            }
        }

        for (int i = 0; i < g.length; i++) {
            for (int j = 0; j < g[0].length; j++) {
                if (g[i][j] == 0) {
                    dfs(g, i, j);
                    res++;
                }
            }
        }
        return res;
    }

    public void dfs(int[][] g, int i, int j) {
        if (i >= 0 && i < g.length && j >= 0 && j < g[0].length && g[i][j] == 0) {
            g[i][j] = 1;
            dfs(g, i + 1, j);
            dfs(g, i - 1, j);
            dfs(g, i, j + 1);
            dfs(g, i, j - 1);
        }
    }
}
