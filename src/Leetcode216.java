import java.util.ArrayList;
import java.util.List;

public class Leetcode216 {
  List<List<Integer>> res = new ArrayList<>();
  public List<List<Integer>> combinationSum3(int k, int n) {
    dfs(new ArrayList<>(), k, 0, 1, n);
    return res;
  }

  public void dfs(List<Integer> curr, int k, int sum, int index, int target) {
    if (index > 9 || curr.size() >= k) {
      if (sum == target && curr.size() == k) {
        res.add(new ArrayList<>(curr));
      }
      return;
    }

    for (int i = index; i <= 9 ; i++) {
      curr.add(i);
      dfs(curr, k, sum + i, i + 1, target);
      curr.remove(curr.size() - 1);
    }
  }
}
