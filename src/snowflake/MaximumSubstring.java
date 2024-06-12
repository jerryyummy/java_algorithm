package snowflake;

import java.util.HashMap;

public class MaximumSubstring {

  static HashMap<String, Integer> map = new HashMap<>();

  public static int getMaxBeautifulSubstrings(String color) {
    return recur(color, 0, color.length() - 1);
  }

  private static int recur(String color, int start, int end) {
    if (start > end) return 0;

    String key = start + "," + end;
    if (map.containsKey(key)) return map.get(key);

    char curr = '.';
    int res = 0;

    if (start == end) return 1;

    for (int i = start; i <= end; i++) {
      if (color.charAt(i) == '.') {
        res = Math.max(res, (i - start + 1) * (i - start + 2) / 2 + recur(color, i + 1, end));
      } else if (curr != '.' && color.charAt(i) != curr) {
        break;
      } else {
        curr = color.charAt(i);
        res = Math.max(res, (i - start + 1) * (i - start + 2) / 2 + recur(color, i + 1, end));
      }
    }

    map.put(key, res);
    return res;
  }

  public static void main(String[] args) {
    String color = "bb.ccc.";
    System.out.println(getMaxBeautifulSubstrings(color));
  }
}

