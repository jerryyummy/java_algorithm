import java.util.Arrays;

public class Leetcode673 {
  public int findNumberOfLIS(int[] nums) {
    if (nums.length == 0) return 0;
    int n = nums.length, maxLength = 0, maxCount = 0;
    int[] lengths = new int[n]; // 每个索引处结束的最长增加子序列的长度
    int[] counts = new int[n]; // 每个索引处结束的最长增加子序列的数量
    Arrays.fill(lengths, 1);
    Arrays.fill(counts, 1);

    for (int i = 1; i < n; i++) {
      for (int j = 0; j < i; j++) {
        if (nums[i] > nums[j]) {
          if (lengths[j] + 1 > lengths[i]) {
            lengths[i] = lengths[j] + 1;
            counts[i] = counts[j];
          } else if (lengths[j] + 1 == lengths[i]) {
            counts[i] += counts[j];
          }
        }
      }
    }

    // 找到LIS的最大长度
    for (int length : lengths) {
      maxLength = Math.max(maxLength, length);
    }

    // 计算存在这个长度的序列有多少个
    for (int i = 0; i < n; i++) {
      if (lengths[i] == maxLength) {
        maxCount += counts[i];
      }
    }

    return maxCount;
  }


  public static void main(String[] args) {
    Leetcode673 solution = new Leetcode673();
    solution.findNumberOfLIS(new int[]{1,3,5,4,7});
  }

}
