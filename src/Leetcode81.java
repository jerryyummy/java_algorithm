public class Leetcode81 {
  public boolean search(int[] nums, int target) {
    int left = 0, right = nums.length - 1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] == target) {
        return true;
      }

      // 当中间值等于右侧值时，无法确定哪侧有序，逐步排除右侧的重复值
      if (nums[mid] == nums[right]) {
        right--; // 这里是关键的修改
      } else if (nums[mid] < nums[right]) { // 右侧有序
        if (nums[mid] < target && target <= nums[right]) {
          left = mid + 1;
        } else {
          right = mid - 1;
        }
      } else { // 左侧有序
        if (nums[left] <= target && target < nums[mid]) {
          right = mid - 1;
        } else {
          left = mid + 1;
        }
      }
    }
    return false;
  }
}
