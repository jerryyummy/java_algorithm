import java.util.ArrayDeque;
import java.util.Deque;

public class Leetcode2334 {
  public int validSubarraySize(int[] nums, int threshold) {
    if (nums == null || nums.length == 0) {
      return -1;
    }

    Deque<Integer> stack = new ArrayDeque<>();
    int n = nums.length;
    int k = 0;
    for (int i = 0; i <= n; i++) {
      int num = i == n ? 0 : nums[i];
      while (!stack.isEmpty() && num < nums[stack.peek()]) {
        int curMinIndex = stack.pop();
        int prevIndex = stack.isEmpty() ? - 1 : stack.peek();
        k = i - prevIndex - 1;
        if (nums[curMinIndex] > threshold / k) {
          return k;
        }
      }
      stack.push(i);
    }
    return -1;
  }
}
