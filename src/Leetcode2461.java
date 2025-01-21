import java.util.HashMap;

public class Leetcode2461 {
  public long maximumSubarraySum(int[] nums, int k) {
    long sum = 0;
    long curSum = 0;
    HashMap<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < k; i++) {
      curSum += nums[i];
      map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
    }

    if (map.size() == k) {
      sum = Math.max(sum,curSum);
    }

    for (int i = k; i < nums.length; i++) {
      map.put(nums[i-k], map.getOrDefault(nums[i-k], 0) - 1);
      if(map.getOrDefault(nums[i-k], 0) == 0) map.remove(nums[i-k]);
      curSum -= nums[i-k];
      map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
      curSum += nums[i];
      if(map.size() == k){
        sum = Math.max(sum,curSum);
      }
    }
    return sum;
  }
}
