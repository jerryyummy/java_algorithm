import java.util.HashMap;
import java.util.Map;

public class Leetcode1062 {
//  public int longestRepeatingSubstring(String s) {
//    int maxLength = 0;
//    Map<String, Integer> map = new HashMap<>();
//    for (int i = 0; i < s.length(); i++) {
//      for (int j = i; j < s.length(); j++) {
//        String sub = s.substring(i, j+1);
//        map.put(sub, map.getOrDefault(sub, 0) + 1);
//        if (map.get(sub) > 1) {
//          maxLength = Math.max(maxLength, j-i+1);
//        }
//      }
//    }
//    return maxLength;
//  }

  public int longestRepeatingSubstring(String S) {
    int l = S.length();
    int[][] dp = new int[l+1][l+1];
    int res = 0;
    for (int i = 1; i <= l; i++) {
      for (int j = i + 1; j <= l; j++) {
        if (S.charAt(i - 1) == S.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1] + 1;
          res = Math.max(dp[i][j],res);
        }
      }
    }
    return res;
  }
}
