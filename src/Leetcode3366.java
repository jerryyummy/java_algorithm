import java.util.Arrays;

public class Leetcode3366 {
  int solve(int[] nums, int k, int[][][] dp, int idx, int op1, int op2) {
    if (idx == nums.length)
      return 0;
    if (dp[idx][op1][op2] != -1)
      return dp[idx][op1][op2];

    int ans = Integer.MAX_VALUE;
    if (op1 != 0)
    {
      //CASE-1
      int sumOp1 = ((int)Math.ceil(nums[idx] / 2.00)) + solve(nums, k, dp, idx + 1, op1 - 1, op2);
      ans = Math.min(ans, sumOp1);
    }
    if (op2 != 0 && nums[idx] >= k)
    {
      //CASE-2
      int sumOp2 = (nums[idx] - k) + solve(nums, k, dp, idx + 1, op1, op2 - 1);
      ans = Math.min(ans, sumOp2);
    }
    if (op1 != 0 && op2 != 0)
    {
      int afterOp1 = (int)Math.ceil(nums[idx] / 2.00);
      if (afterOp1 >= k)
      {
        //CASE-3
        int sumOp1Op2 = ( (afterOp1 - k) + solve(nums, k, dp, idx + 1, op1 - 1, op2 - 1) );
        ans = Math.min(ans, sumOp1Op2);
      }
      if (nums[idx] >= k)
      {
        //CASE-4
        int sumOp2Op1 = ((int)Math.ceil( (nums[idx] - k) / 2.00)) + solve(nums, k, dp, idx + 1, op1 - 1, op2 - 1);
        ans = Math.min(ans, sumOp2Op1);
      }
    }
    //CASE-5
    int sumNoOp = nums[idx] + solve(nums, k, dp, idx + 1, op1, op2);

    ans = dp[idx][op1][op2] = Math.min(ans, sumNoOp);

    return ans;

  }

  public int minArraySum(int[] nums, int k, int op1, int op2) {
    int[][][] dp = new int[nums.length][op1 + 1][op2 + 1];
    for (int i = 0; i < nums.length; i++)
    {
      for (int j = 0; j <= op1; j++)
      {
        Arrays.fill(dp[i][j], -1);
      }
    }

    return solve(nums, k, dp, 0, op1, op2);
  }
}
