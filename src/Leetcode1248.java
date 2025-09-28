public class Leetcode1248 {
    public int numberOfSubarrays(int[] nums, int k) {
        int n = nums.length;
        int[] cnt = new int[n + 1];
        cnt[0] = 1; // 前缀有0个奇数的方案数
        int odd = 0, ans = 0;
        for (int x : nums) {
            if ((x & 1) == 1) odd++;
            if (odd - k >= 0) ans += cnt[odd - k];
            cnt[odd]++;
        }
        return ans;
    }
}
