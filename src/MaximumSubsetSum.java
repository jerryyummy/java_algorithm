public class MaximumSubsetSum {
  public int maximumSubsetSum(int[] nums, int x) {
    int[][] dp = new int[nums.length][2];
    for(int i = 0;i<x;i++){
      if(nums[i]<=0) continue;
      dp[i][0] = 0;
      dp[i][1] = nums[i];
    }
    for(int i = x;i<nums.length;i++){
      dp[i][0] = nums[i]<=0?0:dp[i-x][1];
      dp[i][1] = nums[i]<=0?Math.max(dp[i-x][0],dp[i-x][1]):Math.max(dp[i-x][0],dp[i-x][1])+nums[i];
    }

    int max = 0;
    for (int i = nums.length-x; i < nums.length; i++) {
      max = Math.max(max,Math.max(dp[i][0],dp[i][1]));
    }
    return max;
  }

  public static void main(String[] args) {
    MaximumSubsetSum solution = new MaximumSubsetSum();
    System.out.println(solution.maximumSubsetSum(new int[]{-1,2,3,-4,6,5},2));
    System.out.println(solution.maximumSubsetSum(new int[]{-1,-2,-3,-4,-6,-5},2));
  }
}
