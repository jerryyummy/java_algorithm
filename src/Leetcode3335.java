public class Leetcode3335 {
  private int mod = 1000000007;
  private int[] dp = new int[100100];

  public int lengthAfterTransformations(String s, int t) {
    for (int i = 0; i < 26; ++i)
      dp[i] = 1;
    for (int i = 26; i < 100100; ++i)
      dp[i] = (dp[i - 26] + dp[i - 26 + 1]) % mod;

    int res = 0, n = s.length();
    for (int i = 0; i < n; ++i)
      res = (res + dp[s.charAt(i) - 'a' + t]) % mod;
    return res;
  }
}
