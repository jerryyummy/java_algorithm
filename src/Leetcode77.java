import java.util.ArrayList;
import java.util.List;

public class Leetcode77 {
  List<List<Integer>> res = new ArrayList<>();

  public List<List<Integer>> combine(int n, int k) {
    backtrack(1, n, k, new ArrayList<>());
    return res;
  }

  public void backtrack(int index, int n, int k, List<Integer> cur) {
    if (cur.size() == k) {
      res.add(new ArrayList<>(cur));
      return;
    }
    for (int i = index; i <= n; i++) {
      cur.add(i);
      backtrack(i + 1, n, k, cur); // Move to the next element
      cur.remove(cur.size() - 1);   // Backtrack
    }
  }

}
