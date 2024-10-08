import java.util.Stack;

public class Leetcode907 {
  public int sumSubarrayMins(int[] arr) {
    int MOD = 1000000007;

    Stack<Integer> stack = new Stack<>();
    long sumOfMinimums = 0;

    // building monotonically increasing stack
    for (int i = 0; i <= arr.length; i++) {
      while (!stack.empty() && (i == arr.length || arr[stack.peek()] >= arr[i])) {
        int mid = stack.pop();
        int leftBoundary = stack.empty() ? -1 : stack.peek();
        int rightBoundary = i;

        // count of subarrays where mid is the minimum element
        long count = (mid - leftBoundary) * (rightBoundary - mid) % MOD;

        sumOfMinimums += (count * arr[mid]) % MOD;
        sumOfMinimums %= MOD;
      }
      stack.push(i);
    }

    return (int) (sumOfMinimums);
  }

  /*
  public int sumSubarrayMins(int[] arr) {
        int MOD = 1000000007;

        Stack<Integer> stack = new Stack<>();

        // make a dp array of the same size as the input array `arr`
        int[] dp = new int[arr.length];

        // making a monotonic increasing stack
        for (int i = 0; i < arr.length; i++) {
            // pop the stack until it is empty or
            // the top of the stack is greater than or equal to
            // the current element
            while (!stack.empty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }

            // either the previousSmaller element exists
            if (stack.size() > 0) {
                int previousSmaller = stack.peek();
                dp[i] = dp[previousSmaller] + (i - previousSmaller) * arr[i];
            } else {
                // or it doesn't exist, in this case the current element
                // contributes with all subarrays ending at i
                dp[i] = (i + 1) * arr[i];
            }
            // push the current index
            stack.push(i);
        }

        // Add all elements of the dp to get the answer
        long sumOfMinimums = 0;
        for (int count : dp) {
            sumOfMinimums += count;
            sumOfMinimums %= MOD;
        }

        return (int) sumOfMinimums;
    }
   */

}
