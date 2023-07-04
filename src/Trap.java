import java.util.Stack;

/*
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.
 */
public class Trap {

//    public int trap(int[] height) {
//        int res = 0, mx = 0, n = height.length;
//        int[] dp = new int[n];
//        for (int i = 0; i < n; ++i) {
//            dp[i] = mx;
//            mx = Math.max(mx, height[i]);
//        }
//        mx = 0;
//        for (int i = n - 1; i >= 0; --i) {
//            int temp = Math.min(dp[i], mx);
//            mx = Math.max(mx, height[i]);
//            if (dp[i] - height[i] > 0) res += temp - height[i];
//        }
//        return res;
//    }

//    public int trap(int[] height) {
//        int res = 0, mx = 0, n = height.length;
//        int[] dp = new int[n];
//        for (int i = 0; i < n; ++i) {
//            dp[i] = mx;
//            mx = Math.max(mx, height[i]);
//        }
//        mx = 0;
//        for (int i = n - 1; i >= 0; --i) {
//            dp[i] = Math.min(dp[i], mx);
//            mx = Math.max(mx, height[i]);
//            if (dp[i] - height[i] > 0) res += dp[i] - height[i];
//        }
//        return res;
//    }

    public int trap(int[] height) {
        Stack<Integer> s = new Stack<Integer>();
        int i = 0, n = height.length, res = 0;
        while (i < n) {
            if (s.isEmpty() || height[i] <= height[s.peek()]) {
                s.push(i++);
            } else {
                int level_index = s.pop();//基准
                if (s.isEmpty()) continue;
                res += (Math.min(height[i], height[s.peek()]) - height[level_index]) * (i - s.peek() - 1);
            }
        }
        return res;
    }

}
