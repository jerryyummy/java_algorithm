import java.util.HashMap;

public class Leetcode3209 {
  public long countSubarrays(int[] nums, int k) {
    HashMap<Integer, Integer> prevMap = new HashMap<>();
    long ans = 0;

    for (int i = 0; i < nums.length; i++) {
      int cv = nums[i];
      if (cv == k)
        ans++;
      HashMap<Integer, Integer> currMap = new HashMap<>();

      for (int key : prevMap.keySet()) {
        int andVal = key & cv;
        if (andVal == k) {
          ans += prevMap.get(key);
        }
        currMap.put(andVal, currMap.getOrDefault(andVal, 0) + prevMap.get(key));
      }
      currMap.put(cv, currMap.getOrDefault(cv, 0) + 1);
      prevMap = currMap;
    }
    return ans;
  }
}
