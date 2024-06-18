public class Leetcode1219 {
  int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};
  int res = 0;

  public int getMaximumGold(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (grid[i][j] != 0){
          dfs(grid, i, j, new boolean[m][n], 0);
        }
      }
    }
    return res;
  }

  public void dfs(int[][] grid, int x, int y, boolean[][] visited, int cur) {
    visited[x][y] = true;
    cur+=grid[x][y];
    for(int i = 0; i < directions.length; i++) {
      int newX = x + directions[i][0];
      int newY = y + directions[i][1];
      if (newX>=0 && newX <grid.length && newY>=0 && newY < grid[0].length && !visited[newX][newY]) {
        if(grid[newX][newY] == 0){
          continue;
        }else{
          dfs(grid, newX, newY, visited, cur);
        }
      }
    }
    res = Math.max(res, cur);
    visited[x][y] = false;
  }
}
