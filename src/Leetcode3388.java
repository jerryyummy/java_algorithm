public class Leetcode3388 {
  public int beautifulSplits(int[] nums) {
    int n = nums.length;
    int[][] dp = new int[n][n];
    for(int i=n-2;i>=0;--i){
      for(int j=i+1;j<n;++j){
        if(nums[i] == nums[j]){
          dp[i][j] = 1 + (j > i+1 && j+1 < n ? dp[i+1][j+1] : 0);
        }
      }
    }
    int res = 0;
    for(int i=n-2;i>0;--i) {
      for(int j=i+1;j<n;++j) {
        boolean f = j-i >= i && dp[0][i] >= i;
        boolean f2 = j-i <= n-j && dp[i][j] >= j-i;
        if(f || f2){
          res++;
        }
      }
    }
    return res;
  }
}
