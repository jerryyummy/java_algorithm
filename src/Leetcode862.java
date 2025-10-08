import java.util.*;

public class Leetcode862 {
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        long[] pre = new long[n + 1];
        for (int i = 0; i < n; i++) pre[i + 1] = pre[i] + nums[i];

        int res = n + 1;
        ArrayDeque<Integer> dq = new ArrayDeque<>(); // 存前缀和下标，pre[下标]递增

        for (int i = 0; i <= n; i++) {
            // 从队首尽量取出满足 sum(i..j-1) >= k 的 j
            while (!dq.isEmpty() && pre[i] - pre[dq.peekFirst()] >= k) {
                res = Math.min(res, i - dq.pollFirst());
            }
            // 维持队尾单调递增
            while (!dq.isEmpty() && pre[i] <= pre[dq.peekLast()]) {
                dq.pollLast();
            }
            dq.offerLast(i);
        }

        return res == n + 1 ? -1 : res;
    }
}
