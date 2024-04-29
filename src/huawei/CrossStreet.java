package huawei;

import java.util.Arrays;
import java.util.Scanner;

public class CrossStreet {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int k = in.nextInt();
    int[] a = new int[n];
    for (int i = 0; i < n; i++) {
      a[i] = in.nextInt();
    }
    int b = in.nextInt();
    int m = in.nextInt();
    System.out.println(solve(n,k,a,b,m));
  }

  public static int solve(int n, int k, int[] a, int b, int m){
    if (m==0) return sum(a);
    int[][] dp = new int[n][m+1];
    for (int i = 0, sum = 0; i < n; i++) {
      sum+=a[i];
      dp[i][0] = sum;
    }

    dp[0][1] = Math.min(a[0],0);
    for (int i = 2; i <= m; i++) {
      dp[0][i] = dp[0][1];
    }

    for (int i = 1; i < n; i++) {
      for (int j = 1; j <= m; j++) {
        int min = dp[i-1][j]+a[i];
        for (int l = 1; l <= k && i>=l ; l++) {
          min = Math.min(min,dp[i-l][j-1] +b*l);
        }
        dp[i][j] = min;
      }
    }
    return dp[n-1][m];
  }

  public static int sum(int[] a){
    return Arrays.stream(a).sum();
  }
}
