package snowflake;

public class AirportLimousine {
  public static void main(String[] args) {
    int[][] mat = {
        {0, 1, -1},
        {1, 0, 1},
        {1, 1, 1}
    };
    System.out.println(collectMax(mat)); // 输出示例
  }

  public static int collectMax(int[][] mat) {
    int n = mat.length;
    int m = mat[0].length;
    int passengers = 0;

    // 计算总共的乘客数量
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (mat[i][j] == 1) {
          passengers++;
        }
      }
    }

    int finished = 0;
    while (finished < passengers) {
      boolean[][] visited = new boolean[n][m];
      int temp1 = dfs(mat, new int[]{0, 0}, new int[]{n - 1, m - 1}, visited, new int[][]{{0, 1}, {1, 0}});
      if (temp1 == 0) break;
      else {
        finished += temp1;
        if (finished == passengers) break;
      }
      visited = new boolean[n][m]; // 重置访问矩阵
      int temp2 = dfs(mat, new int[]{n - 1, m - 1}, new int[]{0, 0}, visited, new int[][]{{0, -1}, {-1, 0}});
      finished += temp2;
      if (finished == passengers) break;
    }
    return finished;
  }

  public static int dfs(int[][] mat, int[] start, int[] end, boolean[][] visited, int[][] directions) {
    // 将起始位置标记为已访问
    visited[start[0]][start[1]] = true;
    int res = mat[start[0]][start[1]] == 1 ? 1 : 0;
    mat[start[0]][start[1]] = 0;
    // 如果当前位置就是终点，返回找到的乘客数量
    if (start[0] == end[0] && start[1] == end[1]) {
      return res;
    }

    // 遍历所有可能的下一步位置
    for (int[] direction : directions) {
      int nextX = start[0] + direction[0];
      int nextY = start[1] + direction[1];

      // 检查新位置是否在矩阵范围内且未被访问过
      if (nextX >= 0 && nextX < mat.length && nextY >= 0 && nextY < mat[0].length && !visited[nextX][nextY] && mat[nextX][nextY] >= 0) {
        int temp = dfs(mat, new int[]{nextX, nextY}, end, visited, directions);
        if (temp >= 1) {
          return res + temp;
        }
      }
    }

    // 回溯，将状态重置
    visited[start[0]][start[1]] = false;
    mat[start[0]][start[1]] = res == 1 ? 1 : 0; // 恢复原状态
    return 0;
  }
}

