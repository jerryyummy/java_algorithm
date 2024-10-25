public class Leetcode2328 {
  int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
  int mod = 1000000007;

  public int countPaths(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;
    long[][] dp = new long[m][n];  // DP table to cache results
    long res = 0;

    // Iterate over each cell in the grid and compute the number of paths
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        res = (res + dfs(grid, i, j, dp)) % mod;
      }
    }
    return (int) res;
  }

  public long dfs(int[][] grid, int i, int j, long[][] dp) {
    if (dp[i][j] != 0) return dp[i][j];  // Return cached result if already computed

    long res = 1;  // Each cell itself is a valid path

    for (int[] d : directions) {
      int x = i + d[0];
      int y = j + d[1];
      if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] > grid[i][j]) {
        res = (res + dfs(grid, x, y, dp)) % mod;  // Recursively compute for the next cell
      }
    }

    dp[i][j] = res;  // Cache the result for future reference
    return res;
  }
}
