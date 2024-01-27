import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Leetcode259 {
  public int threeSumSmaller(int[] nums, int target) {
    Arrays.sort(nums);
    int res = 0;
    for (int i = 0; i < nums.length-2;i++) {
      int left = i+1, right = nums.length-1;
      while (left<right){
        if (nums[left]+nums[right]<target-nums[i]){
          res+=(right-left);
          left++;
        }else{
          right--;
        }
      }
    }
    return res;
  }

}
