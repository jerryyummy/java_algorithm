import java.util.Arrays;

public class Leetcode2968 {
    public int maxFrequencyScore(int[] nums, long k) {
        Arrays.sort(nums);
        int n = nums.length;
        long[] s = new long[n + 1];
        for (int i = 0; i < n; i++) {
            s[i + 1] = s[i] + nums[i];
        }

        int ans = 0, left = 0;
        for (int i = 0; i < n; i++) {
            while (distanceSum(s, nums, left, (left + i) / 2, i) > k) {
                left++;
            }
            ans = Math.max(ans, i - left + 1);
        }
        return ans;
    }

    // 把 nums[l] 到 nums[r] 都变成 nums[i]
    long distanceSum(long[] s, int[] nums, int l, int i, int r) {
        long left = (long) nums[i] * (i - l) - (s[i] - s[l]);
        long right = s[r + 1] - s[i + 1] - (long) nums[i] * (r - i);
        return left + right;
    }
}
