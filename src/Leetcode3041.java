import java.util.Arrays;
import java.util.HashMap;

public class Leetcode3041 {
  public int maxSelectedElements(int[] nums) {
    HashMap<Integer, Integer> dp = new HashMap<>();
    Arrays.sort(nums);
    int res = 0;
    for (int a : nums) {
      dp.put(a + 1, dp.getOrDefault(a, 0) + 1);
      dp.put(a, dp.getOrDefault(a - 1, 0) + 1);
      res = Math.max(res, Math.max(dp.get(a), dp.get(a + 1)));
    }
    return res;
  }
}
