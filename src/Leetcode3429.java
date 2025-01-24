import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leetcode3429 {
  public long minCost(int n, int[][] cost) {
    long[][][] dp = new long[n / 2 + 1][4][4];
    for (long[][] layer : dp) {
      for (long[] row : layer) {
        Arrays.fill(row, -1);
      }
    }
    return solve(cost, 3, 3, 0, dp);
  }

  private long solve(int[][] cost, int l, int r, int i, long[][][] dp) {
    int n = cost.length;
    if (i >= n / 2) {
      return 0;
    }
    if (dp[i][l][r] != -1) {
      return dp[i][l][r];
    }
    List<Integer> leftChoices = new ArrayList<>();
    List<Integer> rightChoices = new ArrayList<>();
    for (int j = 0; j < 3; j++) {
      if (l != j) {
        leftChoices.add(j);
      }
      if (r != j) {
        rightChoices.add(j);
      }
    }
    long ans = Long.MAX_VALUE;
    for (int h : leftChoices) {
      for (int j : rightChoices) {
        if (h == j) {
          continue;
        }
        long cl = cost[i][h];
        long cr = cost[n - i - 1][j];
        ans = Math.min(ans, cl + cr + solve(cost, h, j, i + 1, dp));
      }
    }
    return dp[i][l][r] = ans;
  }
}
