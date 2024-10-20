package snowflake;

import java.util.HashMap;
import java.util.Map;

public class VowelSubString {
  public static long vowelsubstring(String s) {
    String vowels = "aeiou";
    long count = 0;
    int lastConsonant = -1;
    Map<Character, Integer> lastSeen = new HashMap<>();

    // Initialize lastSeen with -1 for each vowel
    for (char v : vowels.toCharArray()) {
      lastSeen.put(v, -1);
    }

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (vowels.indexOf(c) != -1) {  // Check if character is a vowel
        lastSeen.put(c, i);  // Update the last seen position for the vowel

        // Check if all vowels have been seen since the last consonant
        boolean allVowelsSeen = true;
        for (int pos : lastSeen.values()) {
          if (pos <= lastConsonant) {
            allVowelsSeen = false;
            break;
          }
        }

        // If all vowels are seen, add the count of new substrings
        if (allVowelsSeen) {
          count += minValue(lastSeen) - lastConsonant;
        }
      } else {
        lastConsonant = i;  // Update last consonant position
      }
    }

    return count;
  }

  // Helper function to find the minimum value in the map
  private static int minValue(Map<Character, Integer> map) {
    int min = Integer.MAX_VALUE;
    for (int value : map.values()) {
      if (value < min) {
        min = value;
      }
    }
    return min;
  }
}
