import java.util.Stack;

public class Leetcode85 {
  public int maximalRectangle(char[][] matrix) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
      return 0;
    }

    int m = matrix.length;
    int n = matrix[0].length;

    int[][] dp = new int[m][n];
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        dp[i][j] = matrix[i][j] == '1' ? 1 : 0;
        if (i > 0 && dp[i][j] == 1) {
          dp[i][j] += dp[i - 1][j]; // Accumulate the height of '1's vertically
        }
      }
    }

    int maxArea = 0;
    for (int i = 0; i < m; i++) {
      maxArea = Math.max(maxArea, largestRectangleArea(dp[i]));
    }

    return maxArea;
  }

  private int largestRectangleArea(int[] heights) {
    Stack<Integer> stack = new Stack<>();
    int maxArea = 0;
    for (int i = 0; i <= heights.length; i++) {
      int h = i == heights.length ? 0 : heights[i];
      while (!stack.isEmpty() && heights[stack.peek()] > h) {
        int height = heights[stack.pop()];
        int width = stack.isEmpty() ? i : i - stack.peek() - 1;
        maxArea = Math.max(maxArea, height * width);
      }
      stack.push(i);
    }
    return maxArea;
  }
}
