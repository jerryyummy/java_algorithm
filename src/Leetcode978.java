public class Leetcode978 {
  public int maxTurbulenceSize(int[] arr) {
    int[][] dp = new int[arr.length][2];
    dp[0][0] = 1;
    dp[0][1] = 1;
    int maxLen = 1; // 初始化最大长度为1，至少有一个元素
    for (int i = 1; i < arr.length; i++) {
      if (arr[i] == arr[i-1]){
        dp[i][0] = 1;
        dp[i][1] = 1;
      }else if (arr[i-1] < arr[i]){
        dp[i][0] = dp[i-1][1]+1;
        dp[i][1] = 1;
      }else{
        dp[i][1] = dp[i-1][0]+1;
        dp[i][0] = 1;
      }
      maxLen = Math.max(maxLen, Math.max(dp[i][0], dp[i][1]));
    }

    return maxLen;
  }
}
