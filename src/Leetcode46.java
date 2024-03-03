import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Leetcode46 {
  List<List<Integer>> res = new ArrayList<>();
  public List<List<Integer>> permute(int[] nums) {
    backtrack(nums,new HashSet<>(), new ArrayList<>());
    return res;
  }

  public void backtrack(int[] nums, HashSet<Integer> visited, List<Integer> cur){
    if (cur.size()==nums.length){
      res.add(new ArrayList<>(cur));
      return;
    }
    for (int num:nums){
      if (!visited.contains(num)){
        cur.add(num);
        visited.add(num);
        backtrack(nums,visited,cur);
        cur.remove(cur.size()-1);
        visited.remove(num);
      }
    }
  }
}
