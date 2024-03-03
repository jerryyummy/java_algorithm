import java.util.ArrayList;
import java.util.List;

public class Leetcode39 {
  List<List<Integer>> res = new ArrayList<>();
  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    backtrack(candidates,0,target,0,new ArrayList<>());
    return res;
  }

  public void backtrack(int[] candidates, int index, int target, int sum, List<Integer> cur){
    if (sum==target){
      res.add(new ArrayList<>(cur));
      return;
    }
    if (sum>target){
      return;
    }else {
      for (int i = index; i < candidates.length; i++) {
        cur.add(candidates[i]);
        backtrack(candidates, i, target, sum+candidates[i], cur);
        cur.remove(cur.size()-1);
      }
    }

  }
}
