import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Leetcode1239 {
  public int maxLength(List<String> arr) {
    Set<Integer> results = new HashSet<>(){{add(0);}};
    int best = 0;

    for (String word : arr)
      best = Math.max(best, addWord(word, results));
    return best;
  }

  private int addWord(String word, Set<Integer> results) {
    int charBitSet = 0;
    for (char c : word.toCharArray()) {
      int mask = 1 << (c - 'a');
      if ((charBitSet & mask) != 0) return 0; // 检测重复字符
      charBitSet |= mask;
    }

    Set<Integer> newResults = new HashSet<>();
    int best = 0;

    for (Integer res : results) {
      if ((res & charBitSet) != 0) continue; // 有重叠字符，跳过

      int newResLen = (res >> 26) + word.length();
      int newCharBitSet = (res | charBitSet) & ((1 << 26) - 1);

      newResults.add((newResLen << 26) | newCharBitSet);
      best = Math.max(best, newResLen);
    }

    results.addAll(newResults);
    return best;
  }
}
