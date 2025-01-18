import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Leetcode451 {
  public String frequencySort(String s) {

    if (s == null || s.isEmpty()) return s;

    char[] chars = s.toCharArray();
    Arrays.sort(chars);

    List<String> charStrings = new ArrayList<String>();
    StringBuilder currentString = new StringBuilder();
    currentString.append(chars[0]);
    for (int i = 1; i < chars.length; i++) {
      if (chars[i] != chars[i - 1]) {
        charStrings.add(currentString.toString());
        currentString = new StringBuilder();
      }
      currentString.append(chars[i]);
    }
    charStrings.add(currentString.toString());

    Collections.sort(charStrings, (a, b) -> b.length() - a.length());

    StringBuilder sb = new StringBuilder();
    for (String str : charStrings) sb.append(str);
    return sb.toString();
  }
}
