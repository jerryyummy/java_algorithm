package mayi;

import java.util.*;

public class MaxGCDPartition {

  public static int maxGCDOfSums(int[] arr) {
    int n = arr.length;
    int sum = Arrays.stream(arr).sum();
    boolean[] dp = new boolean[sum/2 + 1];
    dp[0] = true; // 和为0是可能的（不选任何物品）

    int res = 0;
    // 更新dp数组
    for (int weight : arr) {
      for (int j = sum/2; j >= weight; j--) {
        if (dp[j - weight]) {
          dp[j] = true;
          res = Math.max(res,gcd(j,sum-j));
        }
      }
    }
    return res;
  }

  private static int gcd(int a, int b) {
    while (b != 0) {
      int t = b;
      b = a % b;
      a = t;
    }
    return a;
  }

  public static void main(String[] args) {
    int[] nums = {2,4,6,8,10,12};  // Example array
    System.out.println("Maximum GCD of any two subset sums is: " + maxGCDOfSums(nums));
  }
}
