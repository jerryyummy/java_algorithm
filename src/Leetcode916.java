import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Leetcode916 {
  public List<String> wordSubsets(String[] words1, String[] words2) {
    Map<Character, Integer> maxFrequency = new HashMap<>();
    for (String word : words2) {
      Map<Character, Integer> frequency = getFrequency(word);
      for (Map.Entry<Character, Integer> entry : frequency.entrySet()) {
        char c = entry.getKey();
        int count = entry.getValue();
        maxFrequency.put(c, Math.max(maxFrequency.getOrDefault(c, 0), count));
      }
    }

    List<String> res = new ArrayList<>();
    for (String word : words1) {
      Map<Character, Integer> frequency = getFrequency(word);
      if (isUniversal(frequency, maxFrequency)) {
        res.add(word);
      }
    }

    return res;
  }

  private Map<Character, Integer> getFrequency(String word) {
    Map<Character, Integer> frequency = new HashMap<>();
    for (int i = 0; i < word.length(); i++) {
      char c = word.charAt(i);
      frequency.put(c, frequency.getOrDefault(c, 0) + 1);
    }
    return frequency;
  }

  private boolean isUniversal(Map<Character, Integer> wordFrequency, Map<Character, Integer> maxFrequency) {
    for (Map.Entry<Character, Integer> entry : maxFrequency.entrySet()) {
      char c = entry.getKey();
      int count = entry.getValue();
      if (wordFrequency.getOrDefault(c, 0) < count) {
        return false;
      }
    }
    return true;
  }
}
