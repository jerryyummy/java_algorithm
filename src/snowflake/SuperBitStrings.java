package snowflake;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/*
位运算处理：

使用位运算而不是字符串操作来减少开销。我们通过 num & (1 << i) 来判断某个位是否为 0，通过 num | (1 << i) 来将 0 变为 1。
这样我们可以直接处理整数，避免了不必要的字符串创建和修改。
记忆化递归：

使用一个 Map<Integer, HashSet<Integer>> memo 来存储已经处理过的数字和它们的所有组合，避免重复计算。
在递归过程中，如果一个数字的组合已经被计算过，就直接从缓存中取出结果，而不需要重新计算。
递归生成所有组合：

对于每个 0 位，我们递归地将其变为 1，并继续处理新生成的数字，直到所有可能的组合都被处理。
结果存储在 HashSet 中以确保唯一性。
 */

public class SuperBitStrings {
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
