import java.util.Arrays;
import java.util.HashMap;

public class Leetcode395 {
  public int longestSubstring(String s, int k) {
    /*
    if (s.length() < k) return 0;
    HashMap<Character, Integer> counter = new HashMap();
    for (int i = 0; i < s.length(); i++) {
      counter.put(s.charAt(i), counter.getOrDefault(s.charAt(i), 0) + 1);
    }//记录每个字符的出现次数
    for (char c : counter.keySet()) {
      if (counter.get(c) < k) {//如果改字符出现次数小于k
        int res = 0;
        for (String t : s.split(String.valueOf(c))) {//把字符串切割
          res = Math.max(res, longestSubstring(t, k));
        }
        return res;
      }
    }
    return s.length();
     */

    //滑动窗口解法
    char[] str = s.toCharArray();
    int[] countMap = new int[26];
    int maxUnique = getMaxUniqueLetters(s);
    int result = 0;
    for (int currUnique = 1; currUnique <= maxUnique; currUnique++) {
      // reset countMap
      Arrays.fill(countMap, 0);
      int windowStart = 0, windowEnd = 0, idx = 0, unique = 0, countAtLeastK = 0;
      while (windowEnd < str.length) {
        // expand the sliding window
        if (unique <= currUnique) {//当不同的元素个数小于currUnique
          idx = str[windowEnd] - 'a';
          if (countMap[idx] == 0) unique++;
          countMap[idx]++;
          if (countMap[idx] == k) countAtLeastK++;//该元素个数达到
          windowEnd++;//扩张窗口
        }
        // shrink the sliding window
        else {
          idx = str[windowStart] - 'a';
          if (countMap[idx] == k) countAtLeastK--;
          countMap[idx]--;
          if (countMap[idx] == 0) unique--;
          windowStart++;
        }
        if (unique == currUnique && currUnique == countAtLeastK)
          result = Math.max(windowEnd - windowStart, result);
      }
    }

    return result;
  }

  // get the maximum number of unique letters in the string s
  int getMaxUniqueLetters(String s) {
    boolean[] map = new boolean[26];
    int maxUnique = 0;
    for (int i = 0; i < s.length(); i++) {
      if (!map[s.charAt(i) - 'a']) {
        maxUnique++;
        map[s.charAt(i) - 'a'] = true;
      }
    }
    return maxUnique;
  }

}
