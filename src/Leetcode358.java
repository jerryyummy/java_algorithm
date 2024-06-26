import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Leetcode358 {
  public String rearrangeString(String s, int k) {
    Map<Character, Integer> freqs = new HashMap<>();
    int maxFreq = 0;

    for (char c : s.toCharArray()) {
      freqs.put(c, freqs.getOrDefault(c, 0) + 1);
      maxFreq = Math.max(maxFreq, freqs.get(c));
    }

    Set<Character> mostChars = new HashSet<>();
    Set<Character> secondChars = new HashSet<>();

    for (char c: freqs.keySet()) {
      if (freqs.get(c) == maxFreq) {
        mostChars.add(c);
      } else if (freqs.get(c) == maxFreq - 1) {
        secondChars.add(c);
      }
    }

    StringBuilder[] segments = new StringBuilder[maxFreq];
    for (int i = 0; i < maxFreq; i++) {
      segments[i] = new StringBuilder();

      for (char c: mostChars) {
        segments[i].append(c);
      }

      if (i < maxFreq - 1) {
        for (char c: secondChars) {
          segments[i].append(c);
        }
      }
    }

    int segmentId = 0;

    for (char c: freqs.keySet()) {
      if (mostChars.contains(c) || secondChars.contains(c)) {
        continue;
      }

      for (int freq = freqs.get(c); freq > 0; freq--) {
        segments[segmentId].append(c);
        segmentId = (segmentId + 1) % (maxFreq - 1);
      }
    }

    for (int i = 0; i < maxFreq - 1; i++) {
      if (segments[i].length() < k) {
        return "";
      }
    }

    return String.join("", segments);
  }
}
