package snowflake;

import java.util.Arrays;

public class AirportLimousine {

  public static int collectMax(int[][] grid) {
    int N = grid.length;
    int[][] dp = new int[N][N];//存储到达每个点时的最大价值
    for (int[] row: dp) {
      Arrays.fill(row, Integer.MIN_VALUE);
    }
    dp[0][0] = grid[0][0];

    for (int t = 1; t <= 2*N - 2; ++t) {//这是因为从左上角到右下角需要走N-1步向右和N-1步向下，共2N-2步
      int[][] dp2 = new int[N][N];
      for (int[] row: dp2) {
        Arrays.fill(row, Integer.MIN_VALUE);
      }

      for (int i = Math.max(0, t - (N - 1)); i <= Math.min(N - 1, t); ++i) {
        for (int j = Math.max(0, t - (N - 1)); j <= Math.min(N - 1, t); ++j) {
          if (grid[i][t - i] == -1 || grid[j][t - j] == -1) {
            continue;
          }
          int val = grid[i][t-i];
          if (i != j) {
            val += grid[j][t - j];
          }
          for (int pi = i - 1; pi <= i; ++pi) {//这是(i, j)在上一步(t-1)可能的来源位置
            for (int pj = j - 1; pj <= j; ++pj) {
              if (pi >= 0 && pj >= 0) {
                dp2[i][j] = Math.max(dp2[i][j], dp[pi][pj] + val);
              }
            }
          }
        }
      }
      dp = dp2;
    }
    return Math.max(0, dp[N - 1][N - 1]);
  }

  public static void main(String[] args) {
    int[][] mat = {
        {0, 1,1},
        {1,0,1},
        {1,1,1}
    };
    System.out.println(collectMax(mat));
  }
}

