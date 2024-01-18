import java.util.Arrays;

public class Leetcode1877 {
  public int minPairSum(int[] nums) {
    Arrays.sort(nums);
    int i = 0, j = nums.length-1;
    int res = 0;
    while (i<j){
      int cur = nums[i]+nums[j];
      res = Math.max(res,cur);
      i++;
      j--;
    }
    return res;
  }
}
