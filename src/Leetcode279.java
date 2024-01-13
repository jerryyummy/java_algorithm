public class Leetcode279 {
    public int numSquares(int n) {
        int base = 1;
        int[] dp = new int[n+1];
        for (int i = 1; i <=n ; i++) {
            if (base*base==i){
                dp[i] = 1;
                base+=1;
            }else{
                dp[i] = Integer.MAX_VALUE;
                for (int j = base-1; j > 0 ; j--) {
                    dp[i] = Math.min(dp[i-(j*j)]+1,dp[i]);
                }
            }
        }
        return dp[n];
    }
}
