package Intelligent_information;
//要找出字符串 s 中不包含字符串 t 作为子序列的最长子串的长度
public class SubstringWithoutSubsequence {

  public static void main(String[] args) {
    String s = "abcde";
    String t = "ace";
    System.out.println(longestSubstringWithoutSubsequence(s, t));
  }

  public static int longestSubstringWithoutSubsequence(String s, String t) {
    int maxLength = 0;

    // 外层循环遍历s的所有子串
    for (int start = 0; start < s.length(); start++) {
      int tIndex = 0;
      int currentLength = 0;

      // 内层循环从当前位置开始，查找不包含t的子串
      for (int end = start; end < s.length(); end++) {
        // 如果s中的字符出现在t中，则停止当前循环
        if (tIndex < t.length() && s.charAt(end) == t.charAt(tIndex)) {
          tIndex++;
        }

        if (tIndex == t.length()) {
          break; // 找到t的子序列，停止内层循环
        }

        currentLength++;
        maxLength = Math.max(maxLength, currentLength); // 更新最长子串的长度
      }
    }

    return maxLength;
  }
}
