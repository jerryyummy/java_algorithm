public class Leetcode1049 {
  public int lastStoneWeightII(int[] stones) {
    int totalSum = 0;
    for (int stone : stones) {
      totalSum += stone;
    }

    boolean[] dp = new boolean[totalSum / 2 + 1];
    dp[0] = true;

    for (int stone : stones) {
      for (int j = totalSum / 2; j >= stone; j--) {
        dp[j] = dp[j] || dp[j - stone];
      }
    }

    for (int j = totalSum / 2; ; j--) {
      if (dp[j]) {
        return totalSum - 2 * j;
      }
    }
  }
}
