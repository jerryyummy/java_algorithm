import java.util.ArrayList;
import java.util.List;

public class Leetcode2962 {
  public long countSubarrays(int[] nums, int k) {
    long res = 0;
    List<Integer> position = new ArrayList<Integer>();
    int max = 0;
    for (int i = 0; i < nums.length; i++) {
      max = Math.max(max, nums[i]);
    }

    for (int i = 0; i < nums.length; i++) {
      if (nums[i] == max) {
        position.add(i);
      }
      if(position.size() >= k){
        res += position.get(position.size()-k)+1;
      }
    }

    return res;
  }
}
