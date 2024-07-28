import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {


  public static void main(String[] args) {
    System.out.println(numberOfSubstrings("00011"));
  }

  public static int numberOfSubstrings(String s) {
    int n = s.length();
    int dominantCount = 0;
    int left = 0;
    Map<Character, Integer> charCount = new HashMap<>();

    for (int right = 0; right < n; right++) {
      char ch = s.charAt(right);
      charCount.put(ch, charCount.getOrDefault(ch, 0) + 1);

      while (charCount.getOrDefault('1', 0) >= charCount.getOrDefault('0', 0) * charCount.getOrDefault('0', 0)) {
        dominantCount += n - right; // Add the number of possible substrings ending at right
        char leftChar = s.charAt(left);
        charCount.put(leftChar, charCount.get(leftChar) - 1);
        left++;
      }
    }

    return dominantCount;
  }
}
