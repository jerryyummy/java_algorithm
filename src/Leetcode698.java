import java.util.Arrays;

public class Leetcode698 {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int totalSum = 0;
        for (int num : nums) totalSum += num;
        if (totalSum % k != 0) return false;

        int target = totalSum / k;
        boolean[] used = new boolean[nums.length];

        // 排序优化：从大到小尝试，尽早剪枝
        Arrays.sort(nums);
        reverse(nums);

        return backtrack(nums, used, 0, 0, k, target);
    }

    private boolean backtrack(int[] nums, boolean[] used, int start, int currSum, int k, int target) {
        if (k == 0) return true;
        if (currSum == target) {
            // 当前子集完成，开始下一个
            return backtrack(nums, used, 0, 0, k - 1, target);
        }

        for (int i = start; i < nums.length; i++) {
            if (used[i] || currSum + nums[i] > target) continue;

            used[i] = true;
            if (backtrack(nums, used, i + 1, currSum + nums[i], k, target)) return true;
            used[i] = false;

            // 剪枝优化：如果当前数无法作为第一个元素组合成功，那么其他也不行
            if (currSum == 0) break;
        }

        return false;
    }

    private void reverse(int[] nums) {
        int i = 0, j = nums.length - 1;
        while (i < j) {
            int tmp = nums[i];
            nums[i++] = nums[j];
            nums[j--] = tmp;
        }
    }
}
