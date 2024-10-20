package snowflake;

public class ApproximateMatch {

  // 构建部分匹配表（LPS 数组）
  public static int[] buildLPS(String pattern) {
    int m = pattern.length();
    int[] lps = new int[m];
    int len = 0;
    int i = 1;

    // 构建 LPS 表
    while (i < m) {
      if (pattern.charAt(i) == pattern.charAt(len)) {
        len++;
        lps[i] = len;
        i++;
      } else {
        if (len != 0) {
          len = lps[len - 1];
        } else {
          lps[i] = 0;
          i++;
        }
      }
    }
    return lps;
  }

  // 使用 KMP 算法查找最长的后缀匹配 (prefix 的后缀和 text 的任意子串)
  public static int longestSuffixMatchKMP(String text, String pattern) {
    int n = text.length();
    int m = pattern.length();
    int[] lps = buildLPS(pattern); // 构建 LPS 表

    int i = 0; // text 的指针
    int j = 0; // pattern 的指针
    int maxMatch = 0;

    // 使用 KMP 进行模式匹配
    while (i < n) {
      if (text.charAt(i) == pattern.charAt(j)) {
        i++;
        j++;

        // 更新最大匹配长度，记录 pattern 的匹配部分
        if (j > maxMatch) {
          maxMatch = j;
        }

        // 当 pattern 完全匹配时，重置 j
        if (j == m) {
          j = lps[j - 1];
        }
      } else {
        if (j != 0) {
          j = lps[j - 1]; // 使用 LPS 表跳过一些字符
        } else {
          i++; // 匹配失败时，移动 text 的指针
        }
      }
    }

    return maxMatch; // 返回匹配的最大后缀长度
  }

  // 查找 `prefixString` 的所有后缀，并使用 KMP 匹配 `text` 中的子串
  public static int longestSuffixMatch(String text, String prefixString) {
    int maxMatch = 0;

    // 遍历 `prefixString` 的每个后缀，并在 `text` 中寻找匹配
    for (int i = 0; i < prefixString.length(); i++) {
      String suffix = prefixString.substring(i); // 获取 `prefixString` 的后缀
      int matchLength = longestSuffixMatchKMP(text, suffix); // KMP 匹配后缀
      maxMatch = Math.max(maxMatch, matchLength); // 更新最大匹配长度
    }

    return maxMatch;
  }

  // 核心方法，计算得分并返回最佳子串
  public static String calculateScore(String text, String prefixString, String suffixString) {
    int bestScore = -1;
    String bestSubstring = "";

    // 遍历所有子串，计算每个子串的得分
    for (int i = 0; i < text.length(); i++) {
      for (int j = i + 1; j <= text.length(); j++) {
        String substring = text.substring(i, j);
        int prefixScore = longestSuffixMatch(substring, prefixString); // 匹配前缀（后缀匹配）
        int suffixScore = longestSuffixMatchKMP(substring, suffixString); // 匹配后缀（前缀匹配）
        int textScore = prefixScore + suffixScore;
        //System.out.println("prefix: "+prefixString.substring(prefixString.length()-prefixScore));
        //System.out.println("suffix: "+suffixString.substring(0,suffixScore));
        // 如果前缀和后缀匹配部分重叠，得分为子串长度
        if (prefixScore + suffixScore > substring.length()) {
          textScore = substring.length(); // 有重叠，取整个子串
        }

        // 更新最佳子串
        if (textScore > bestScore || (textScore == bestScore && substring.compareTo(bestSubstring) < 0)) {
          bestScore = textScore;
          bestSubstring = substring;
        }
      }
    }

    return bestSubstring;
  }

  public static void main(String[] args) {
    // 测试用例
    String text = "banana";
    String prefixString = "bana";
    String suffixString = "nana";

    String bestSubstring = calculateScore(text, prefixString, suffixString);
    System.out.println("最佳子串是: " + bestSubstring);
  }

}
