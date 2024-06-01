package snowflake;

import java.util.*;

//Leetcode 1639
public class NumWays {
  static final int mod = 1_000_000_007;

  static int numWays(List<String> words, String target) {
    int m = words.size(), n = words.get(0).length(), k = target.length();

    //预处理 在第i个位置 有多少个'a'+j字符
    int[][] cnts = new int[n][26];
    for (String s : words) {
      for (int i = 0; i < n; i++) {
        cnts[i][s.charAt(i) - 'a']++;
      }
    }
    //状态转移 dp[i][j] = dp[i][j-1] + dp[i-1][j-1] * cnts[j][ts[i]-'a']
    int[][] dp = new int[k + 1][n + 1];
    Arrays.fill(dp[0], 1);

    for (int i = 0; i < k; i++) {
      int idx = target.charAt(i) - 'a';
      int t = n - (k - 1 - i);
      for (int j = i; j < t; j++) {
        long dpij = dp[i + 1][j] + (long) dp[i][j] * cnts[j][idx];
        dp[i + 1][j + 1] = (int) (dpij % mod);
      }
    }
    return dp[k][n];
  }

  public static void main(String[] args) {
    List<String> words = new ArrayList<>();
    words.add("ace");
    words.add("dgf");
    words.add("ade");
    System.out.println(numWays(words,"ae"));
  }
}
