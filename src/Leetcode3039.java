import java.util.ArrayList;
import java.util.HashMap;

public class Leetcode3039 {
  public String lastNonEmptyString(String s) {
    HashMap<Character, Integer> charFrequency = new HashMap<>();
    HashMap<Character, Integer> charLastIndex = new HashMap<>();

    for (int i = 0; i < s.length(); i++) {
      charLastIndex.put(s.charAt(i), i);
      charFrequency.put(s.charAt(i), charFrequency.getOrDefault(s.charAt(i), 0) + 1);
    }

    int maxFrequency = 0;
    for (int frequency : charFrequency.values()) {
      maxFrequency = Math.max(maxFrequency, frequency);
    }

    StringBuilder result = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      char currentChar = s.charAt(i);
      if (charFrequency.get(currentChar) == maxFrequency && charLastIndex.get(currentChar) == i) {
        result.append(currentChar);
      }
    }
    return result.toString();
  }
}
