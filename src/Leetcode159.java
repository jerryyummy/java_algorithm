import java.util.HashMap;

public class Leetcode159 {
  public int lengthOfLongestSubstringTwoDistinct(String s) {
    int maxLength = 0;
    int left = 0;
    HashMap<Character, Integer> charCountMap = new HashMap<>();

    for (int right = 0; right < s.length(); right++) {
      char currentChar = s.charAt(right);
      charCountMap.put(currentChar, charCountMap.getOrDefault(currentChar, 0) + 1);

      while (charCountMap.size() > 2) {
        char leftChar = s.charAt(left);
        charCountMap.put(leftChar, charCountMap.get(leftChar) - 1);
        if (charCountMap.get(leftChar) == 0) {
          charCountMap.remove(leftChar);
        }
        left++;
      }

      maxLength = Math.max(maxLength, right - left + 1);
    }

    return maxLength;
  }

  public static void main(String[] args) {
    Leetcode159 leetcode159 = new Leetcode159();
    System.out.println(leetcode159.lengthOfLongestSubstringTwoDistinct("eceba"));
  }
}
