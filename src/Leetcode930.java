import java.util.HashMap;
import java.util.Map;

public class Leetcode930 {
  public int numSubarraysWithSum(int[] nums, int goal) {
    int curSum = 0;
    int res = 0;
    Map<Integer, Integer> prefixSumCount = new HashMap<>();

    prefixSumCount.put(0, 1); // 初始化前缀和为0的情况

    for (int num : nums) {
      curSum += num;

      res += prefixSumCount.getOrDefault(curSum - goal, 0);

      prefixSumCount.put(curSum, prefixSumCount.getOrDefault(curSum, 0) + 1);
    }

    return res;
  }
}
