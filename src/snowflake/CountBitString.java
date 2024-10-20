package snowflake;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class CountBitString {
  public static int superBitstrings(int n, List<Integer> bitStrings) {
    HashSet<Integer> set = new HashSet<>();
    Map<Integer, HashSet<Integer>> memo = new HashMap<>();

    for (int num : bitStrings) {
      generateCombinations(num, n, set, memo);
    }

    return set.size();
  }

  public static void generateCombinations(int num, int n, HashSet<Integer> set, Map<Integer, HashSet<Integer>> memo) {
    if (memo.containsKey(num)) {
      set.addAll(memo.get(num));
      return;
    }

    HashSet<Integer> currentSet = new HashSet<>();
    currentSet.add(num);

    for (int i = 0; i < n; i++) {
      if ((num & (1 << i)) == 0) {
        int newNum = num | (1 << i);
        generateCombinations(newNum, n, set, memo);
        currentSet.add(newNum);
      }
    }

    memo.put(num, currentSet);
    set.addAll(currentSet);
  }
}
