package yotta;

public class DisjointLine {
  public static int getDisjointLines(int n) {
    if (n%2==0){
      return catalan(n/2);
    }else{
      return n*catalan((n-1)/2);
    }
  }

  public static int catalan(int n) {
    if (n <= 1) {
      return 1;
    }

    // 初始化动态规划表
    int[] dp = new int[n + 1];
    dp[0] = 1;
    dp[1] = 1;

    // 动态规划填表
    for (int i = 2; i <= n; i++) {
      for (int j = 0; j < i; j++) {
        dp[i] += dp[j] * dp[i - j - 1];
      }
    }

    return dp[n];
  }

  public static void main(String[] args) {
    System.out.println(getDisjointLines(8));
  }
}
