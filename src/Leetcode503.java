import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Leetcode503 {
  public int[] nextGreaterElements(int[] nums) {
    int[] res = new int[nums.length];
    int n = nums.length;
    Stack<Integer> stack = new Stack<>();
    int[] temp = new int[2*nums.length];
    for (int i = 0; i < nums.length; i++) {
      temp[i] = nums[i];
      temp[i+n] = nums[i];
    }

    for (int i = 2*n-1; i >= n; i--) {
      stack.push(temp[i]);
    }

    for (int i = n-1; i >= 0; i--) {
      while (!stack.isEmpty() && stack.peek() <= temp[i]) {
        stack.pop();
      }
      res[i] = stack.isEmpty() ? -1 : stack.peek();
      stack.push(temp[i]);
    }
    return res;
  }

  public static void main(String[] args) {
    Leetcode503 leetcode503 = new Leetcode503();
    int[] nums = new int[]{1,2,1};
    int[] res = leetcode503.nextGreaterElements(nums);
    for (int i = 0; i < res.length; i++) {
      System.out.println(res[i]);
    }
  }
}
