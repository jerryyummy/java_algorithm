public class Leetcode1411 {
    public static int numOfWays(int n) {
        int mod = 1000000007;
        int[][] rows = new int[12][3];
        int idx = 0;
        for(int a = 0;a<3;a++){
            for(int b = 0;b<3 ;b++){
                if(a == b) continue;
                for(int c = 0;c<3;c++){
                    if(c == b) continue;
                    rows[idx][0] = a;
                    rows[idx][1] = b;
                    rows[idx][2] = c;
                    idx++;
                }
            }
        }

        long[][] dp = new long[n][idx];
        for (int i = 0; i < idx; i++) {
            dp[0][i] = 1;
        }

        for(int r = 1;r<n;r++){
            for(int j = 0;j<idx;j++){
                if(dp[r-1][j] == 0) continue;
                for(int k = 0;k<idx;k++){
                    // can not keep the same color as previous row
                    if(rows[j][0] == rows[k][0]) continue;
                    if(rows[j][1] == rows[k][1]) continue;
                    if(rows[j][2] == rows[k][2]) continue;
                    dp[r][k] = (dp[r][k] + dp[r-1][j])%mod;
                }
            }
        }

        long res = 0;
        for (int i = 0; i < idx; i++) {
            res = (res + dp[n-1][i])%mod;
        }
        return (int)(res%mod);
    }

    public static void main(String[] args) {
        System.out.println(numOfWays(5000));
    }
}
