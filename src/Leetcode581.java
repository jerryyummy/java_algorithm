import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class Leetcode581 {
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        Deque<Integer> stack = new ArrayDeque<>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            // 单调递增栈：一旦发现下降，就更新违序段的 min / max
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                min = Math.min(min, nums[i]);              // 当前更小值
                max = Math.max(max, nums[stack.pop()]);    // 左侧被弹出的更大值
            }
            stack.push(i);
        }

        // 已经有序
        if (min == Integer.MAX_VALUE) return 0;

        // 左边界：第一个 > min 的位置
        int l = 0;
        while (l < n && nums[l] <= min) l++;

        // 右边界：最后一个 < max 的位置
        int r = n - 1;
        while (r >= 0 && nums[r] >= max) r--;

        return r - l + 1;
    }
}
