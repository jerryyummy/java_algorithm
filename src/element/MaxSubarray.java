package element;

public class MaxSubarray {
  public long maxSubArray(int[] nums) {
    int n = nums.length;
    long[] dp = new long[n];

    for (int i = 0; i < n; i++) {
      long max = nums[i];
      long min = nums[i];
      dp[i] = (i>0)?dp[i-1]:0;
      for (int j = i; j >= 0 ; j--) {
        max = Math.max(max, nums[j]);
        min = Math.min(min, nums[j]);
        if(j>0) {
          dp[i] = Math.max(dp[i],dp[j-1]+max-min);
        } else {
          dp[i] = Math.max(dp[i],max-min);
        }
      }
    }

    return dp[n-1];
  }

  public static void main(String[] args) {
    MaxSubarray maxSubarray = new MaxSubarray();
    System.out.println(maxSubarray.maxSubArray(new int[]{1,1,4,5,1,4}));
  }
}
