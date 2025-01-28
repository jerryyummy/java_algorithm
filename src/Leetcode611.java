import java.util.Arrays;

public class Leetcode611 {
  public int triangleNumber(int[] nums) {
    Arrays.sort(nums);
    int res = 0;

    for (int k = nums.length - 1; k >= 2; k--) {
      int left = 0, right = k - 1;

      while (left < right) {
        if (nums[left] + nums[right] > nums[k]) {
          res += (right - left);
          right--;
        } else {
          left++;
        }
      }
    }
    return res;
  }
}
