import java.util.Arrays;

public class Leetcode304 {
  int[][] dp;
  public Leetcode304(int[][] matrix) {
    int rows = matrix.length;
    int cols = matrix[0].length;
    dp = new int[rows+1][cols+1];
    for (int i = 1; i <= rows; i++) {
      for (int j = 1; j <= cols; j++) {
        dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + matrix[i - 1][j - 1];
      }
    }
  }

  public int sumRegion(int row1, int col1, int row2, int col2) {
    return dp[row2 + 1][col2 + 1] - dp[row2 + 1][col1] - dp[row1][col2 + 1] + dp[row1][col1];
  }

}
