import java.util.Arrays;

public class Leetcode3180 {
  public int maxTotalReward(int[] rewardValues) {
    Arrays.sort(rewardValues);
    int n = rewardValues.length;

    int sum = 0;
    for(int i: rewardValues) sum += i;

    int[] dp = new int[sum+1];
    Arrays.fill(dp, -1);

    return helper(rewardValues, dp, 0, n, 0);
  }

  int helper(int[] rewardValues, int[] dp, int i, int n, int currReward) {
    if(i == n) {
      return currReward;
    }

    if(dp[currReward] != -1) return dp[currReward];

    int notInclude = helper(rewardValues, dp, i+1, n, currReward);

    int include = 0;
    if(rewardValues[i] > currReward) {
      include = helper(rewardValues, dp, i+1, n, currReward+rewardValues[i]);
    }

    dp[currReward] = Math.max(notInclude, include);
    return dp[currReward];
  }
}
