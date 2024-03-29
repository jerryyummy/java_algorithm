import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Leetcode47 {
  public List<List<Integer>> permuteUnique(int[] nums) {
    List<List<Integer>> results = new ArrayList<>();

    // count the occurrence of each number
    HashMap<Integer, Integer> counter = new HashMap<>();
    for (int num : nums) {
      if (!counter.containsKey(num))
        counter.put(num, 0);
      counter.put(num, counter.get(num) + 1);
    }

    LinkedList<Integer> comb = new LinkedList<>();
    this.backtrack(comb, nums.length, counter, results);
    return results;
  }

  protected void backtrack(
      LinkedList<Integer> comb,
      int n,
      HashMap<Integer, Integer> counter,
      List<List<Integer>> results) {

    if (comb.size() == n) {
      results.add(new ArrayList<Integer>(comb));
      return;
    }

    for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
      int num = entry.getKey();
      int count = entry.getValue();
      if (count == 0)
        continue;
      // add this number into the current combination
      comb.addLast(num);
      counter.put(num, count - 1);

      // continue the exploration
      backtrack(comb, n, counter, results);

      // revert the choice for the next exploration
      comb.removeLast();
      counter.put(num, count);
    }
  }

}
