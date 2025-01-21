import java.util.Arrays;

public class Leetcode2563 {
  public long countFairPairs(int[] nums, int lower, int upper) {
    Arrays.sort(nums);
    long res = 0;

    for (int i = 0; i < nums.length; i++) {
      int min = lower - nums[i];
      int max = upper - nums[i];
      res += find(nums, min, max, i + 1);
    }

    return res;
  }

  public long find(int[] nums, int lower, int upper, int start) {
    int left = start, right = nums.length - 1;

    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] < lower) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    int lowIndex = left;
    left = start;
    right = nums.length - 1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] > upper) {
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    int highIndex = right;
    return Math.max(0, highIndex - lowIndex + 1);
  }
}
