import java.util.Arrays;

public class Leetcode16 {
  public int threeSumClosest(int[] nums, int target) {
    Arrays.sort(nums); // 排序数组
    int closest = Integer.MAX_VALUE; // 用于存储当前最接近的和
    int result = 0; // 用于存储最终结果
    int n = nums.length;

    for (int i = 0; i < n - 2; i++) { // 遍历第一个数
      int left = i + 1, right = n - 1; // 双指针寻找剩余两数
      while (left < right) {
        int sum = nums[i] + nums[left] + nums[right]; // 计算当前和
        int diff = Math.abs(sum - target); // 计算与目标的差距

        if (diff < closest) { // 更新最接近的和
          closest = diff;
          result = sum;
        }

        if (sum < target) {
          left++; // 和小于目标，移动左指针增大和
        } else if (sum > target) {
          right--; // 和大于目标，移动右指针减小和
        } else {
          return sum; // 如果刚好等于目标，直接返回
        }
      }
    }

    return result;
  }
}
