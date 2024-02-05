package snowflake;

import java.util.ArrayList;
import java.util.List;

public class UnequalElements {

  private static int n; // Assuming 'n' is initialized elsewhere
  private static int[][] data; // Assuming 'data' is initialized elsewhere
  private static int[] nxt; // Assuming 'nxt' is initialized elsewhere

  public static int dp(int i, int k, int diff) {
    if (i == n) {
      return 0;
    }

    int ans = dp(i + 1, k, 1);

    if (k - diff >= 0) {
      // you can choose this element
      ans = Math.max(ans, dp(nxt[i], k - diff, 0) + data[i][1]);
      ans = Math.max(ans, dp(i + 1, k - diff, 1) + data[i][1]);
    }

    return ans;
  }

  // Method to find the maximum length of subsequence with unequal adjacent elements
  public static int findMaxLength(int[] skills, int k) {
    return dp(0,k,0);
  }

  // Main method for testing the algorithm
  public static void main(String[] args) {
    int[] skills = {1, 2, 3, 2, 1};
    int k = 2;
    System.out.println("Maximum length of subsequence: " + findMaxLength(skills, k));
  }
}


