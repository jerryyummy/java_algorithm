public class Leetcode1155 {
    public int numRollsToTarget(int n, int k, int target) {
        int[][] dp = new int[n][target + 1];
        int mod = (int)1e9 + 7;
        for (int i = 1; i <= Math.min(k, target); i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = i; j <= target; j++) {
                for (int k1 = 1; k1 <= k; k1++) {
                    if(j-k1>=0){
                        dp[i][j] = (dp[i][j]+(dp[i-1][j-k1]))%mod;
                    }
                }
            }
        }
        return dp[n-1][target];
    }
}
