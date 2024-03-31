public class Leetcode416 {
  public boolean canPartition(int[] nums) {
    int sum = 0;
    for(int num:nums) sum+=num;
    if(sum%2==1) return false;
    int target = sum/2;
    boolean[] dp = new boolean[target+1];
    dp[0] = true;
    for (int i = 0; i < nums.length; i++) {
      int num = nums[i];
      for (int j = target; j >= num; --j) {
        dp[j] |= dp[j - num];
      }
    }
    return dp[target];
  }
}
