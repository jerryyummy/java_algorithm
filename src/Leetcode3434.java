import java.util.Arrays;

public class Leetcode3434 {
  public int maxFrequency(int[] nums, int k) {
    int n = nums.length;
    int[] freq = new int[51];
    int[] suffix = new int[n];

    if (nums[n - 1] == k) {
      suffix[n - 1]++;
    }

    for (int i = n - 2; i >= 0; i--) {
      suffix[i] = suffix[i + 1];
      if (nums[i] == k) {
        suffix[i]++;
      }
    }

    int j = 0;
    int ans = suffix[0];
    int[] last = new int[51];
    Arrays.fill(last, -1);

    while (j < n) {
      if (nums[j] != k) {
        if (last[nums[j]] != -1) {
          freq[nums[j]] -= (suffix[last[nums[j]]] - suffix[j]);
        }
        if (freq[nums[j]] < 0) {
          freq[nums[j]] = 0;
        }
        last[nums[j]] = j;
        freq[nums[j]]++;
      }

      for (int i = 0; i < 51; i++) {
        ans = Math.max(ans, (j > 0 ? suffix[0] - suffix[j] : 0) + (j < n - 1 ? suffix[j + 1] : 0) + freq[i]);
      }
      j++;
    }

    return ans;
  }
}
