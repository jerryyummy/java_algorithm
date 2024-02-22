/*
Given n non-negative integers representing an elevation map where the width of each bar is 1,
compute how much water it can trap after raining.
 */
public class Leetcode42 {
    public int trap(int[] height) {
        int res = 0, mx = 0, n = height.length;
        int[] dp = new int[n];
        for (int i = 0; i < n; ++i) {
            dp[i] = mx;
            mx = Math.max(mx, height[i]);
        }
        mx = 0;
        for (int i = n - 1; i >= 0; --i) {
            int temp = Math.min(dp[i], mx);
            mx = Math.max(mx, height[i]);
            if (temp - height[i] > 0) res += temp - height[i];
        }
        return res;
    }

    /*使用单调栈
    public int trap(int[] height) {
        Deque<Integer> stack = new ArrayDeque<>();
        int res = 0;
        for(int i = 0;i<height.length;i++){
            if(stack.isEmpty()){
                stack.push(i);
            }else if(height[stack.peek()]>=height[i]){
                stack.push(i);
            }else{
                while(!stack.isEmpty() && height[stack.peek()]<height[i]){
                    int low = height[stack.pop()];
                    if(!stack.isEmpty()){
                        res+= (Math.min(height[stack.peek()],height[i])-low) * (i-stack.peek()-1);
                    }
                }
                stack.push(i);
            }
        }
        return res;
    }
     */

}