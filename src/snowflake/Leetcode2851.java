package snowflake;

import java.util.ArrayList;
import java.util.List;

public class Leetcode2851 {
  private static final long MOD = (long) 1e9 + 7;
  public int numberOfWays(String s, String t, long k) {
    List<Integer> lps = new ArrayList<>();
    lps.add(0);
    for (int i = 0; i < t.length(); i++) {
      int index = lps.get(i);
      while (index > 0 && s.charAt(index) != t.charAt(i))
        index = lps.get(index - 1);
      lps.add(s.charAt(index) == t.charAt(i) ? index + 1 : 0);
    }
    List<Integer> occur = new ArrayList<>();
    for (int i = 0; i < lps.size(); i++) {
      int index = lps.get(i);
      if (index!=0 && (s.substring(index)+s.substring(0,index)).equals(t)) occur.add(index);
    }
    long[][] dp = {
        {occur.size() - 1, occur.size()},
        {s.length() - occur.size(), s.length() - 1 - occur.size()},
    };
    dp = pow(dp, k);
    return s.equals(t) ? (int) dp[0][1] : (int) dp[0][0];
  }

  private long[][] pow(long[][] a, long n) {
    long[][] res = {{1, 0}, {0, 1}};
    for (; n > 0; n /= 2) {
      if (n % 2 > 0) {
        res = multiply(res, a);
      }
      a = multiply(a, a);
    }
    return res;
  }

  private long[][] multiply(long[][] a, long[][] b) {
    long[][] c = new long[2][2];
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        c[i][j] = (a[i][0] * b[0][j] + a[i][1] * b[1][j]) % MOD;
      }
    }
    return c;
  }


  public static void main(String[] args) {
    Leetcode2851 solution = new Leetcode2851();
    solution.numberOfWays("ababab","ababab",1);
  }

}
