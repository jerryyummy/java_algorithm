public class Leetcode2466 {
  public int countGoodStrings(int low, int high, int zero, int one) {
    int mod = (int) (1e9+7);
    int[] dp = new int[high+1];
    dp[0] = 1;
    for (int i = 1; i <= high ; i++) {
      if (i-zero>=0){
        dp[i] = (dp[i-zero]+dp[i])%mod;
      }
      if (i-one>=0){
        dp[i] = (dp[i-one]+dp[i])%mod;
      }
    }

    int sum = 0;
    for (int i = low ;i <= high ; i++) {
      sum = (sum+dp[i])%mod;
    }

    return sum;
  }
}
