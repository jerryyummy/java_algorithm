public class Leetcode2892 {
  public int minArrayLength(int[] nums, int k) {
    final int n = nums.length;
    int r = 0;
    for (int i = 0; i < n;) {
      int j = i;
      long t = 1;
      while (j < n && (t *= nums[j]) <= k) {
        if (nums[j] == 0) return 1;
        j++;
      }
      r++;
      i = Math.max(i + 1, j);
    }
    return r;
  }
}
