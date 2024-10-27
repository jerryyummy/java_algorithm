import java.util.Arrays;

class Leetcode3336 {
  private static final int MOD = 1_000_000_007;

  public int subsequencePairCount(int[] nums) {
    int n = nums.length;
    int m = 0;
    for (int x : nums) {
      m = Math.max(m, x);
    }
    int[][][] memo = new int[n][m + 1][m + 1];
    for (int[][] mat : memo) {
      for (int[] row : mat) {
        Arrays.fill(row, -1); // -1 表示没有计算过
      }
    }
    return (dfs(n - 1, 0, 0, nums, memo) - 1 + MOD) % MOD; // +MOD 防止减一后变成负数
  }

  int dfs(int i, int j, int k, int[] nums, int[][][] memo) {
    if (i < 0) {
      return j == k ? 1 : 0;
    }
    if (memo[i][j][k] < 0) {
      long res = (long) dfs(i - 1, j, k, nums, memo) +
          dfs(i - 1, gcd(j, nums[i]), k, nums, memo) +
          dfs(i - 1, j, gcd(k, nums[i]), nums, memo);
      memo[i][j][k] = (int) (res % MOD);
    }
    return memo[i][j][k];
  }

  private int gcd(int a, int b) {
    while (a != 0) {
      int tmp = a;
      a = b % a;
      b = tmp;
    }
    return b;
  }
}
