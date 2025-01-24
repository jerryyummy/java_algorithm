import java.util.Arrays;

public class Leetcode1671 {
  public int minimumMountainRemovals(int[] nums) {
    int n = nums.length;
    int[] extendedNums = new int[n + 2];
    extendedNums[0] = Integer.MAX_VALUE;
    extendedNums[n + 1] = Integer.MAX_VALUE;
    System.arraycopy(nums, 0, extendedNums, 1, n);
    n += 2;

    int[] prefixLis = new int[n];
    int[] suffixLis = new int[n];
    Arrays.fill(prefixLis, 1);
    Arrays.fill(suffixLis, 1);

    for (int i = 1; i < n; i++) {
      for (int j = 0; j < i; j++) {
        if (extendedNums[i] > extendedNums[j]) {
          prefixLis[i] = Math.max(prefixLis[i], prefixLis[j] + 1);
        }
      }
    }

    for (int i = n - 2; i >= 0; i--) {
      for (int j = n - 1; j > i; j--) {
        if (extendedNums[i] > extendedNums[j]) {
          suffixLis[i] = Math.max(suffixLis[i], suffixLis[j] + 1);
        }
      }
    }

    int res = Integer.MAX_VALUE;
    for (int i = 1; i < n - 1; i++) {
      if (prefixLis[i] > 1 && suffixLis[i] > 1) {
        res = Math.min(res, (n - 2) - (prefixLis[i] + suffixLis[i] - 1));
      }
    }
    return res;
  }
}
