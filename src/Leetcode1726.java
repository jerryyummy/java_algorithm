import java.util.HashMap;
import java.util.Map;

public class Leetcode1726 {
  public int tupleSameProduct(int[] nums) {
    int res = 0;
    Map<Integer, Integer> map = new HashMap<>();


    for (int i = 0; i < nums.length-1; i++) {
      for (int j = i+1; j < nums.length; j++) {
        map.put(nums[i]*nums[j], map.getOrDefault(nums[i]*nums[j], 0) + 1);
      }
    }

    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
      res += (entry.getValue()*(entry.getValue()-1)/2)*8;
    }
    return res;
  }
}
