package snowflake;

import java.util.Arrays;

public class EfficientCost {

  public int calculateCost(int[] arr, int threshold){
    int[] dp = new int[arr.length+1];
    dp[0] = 0;
    for (int i = 0; i < arr.length; i++) {
      int cur = arr[i];
      dp[i+1] = Integer.MAX_VALUE;
      for (int j = i; j >= Math.max(0,i-threshold+1) ; j--) {
        cur = Math.max(cur,arr[j]);
        dp[i+1] = Math.min(dp[i+1],cur+dp[j]);
      }
    }
    return dp[arr.length];
  }

  public static void main(String[] args) {
    EfficientCost solution = new EfficientCost();
    System.out.println(solution.calculateCost(new int[]{1,5,6,7,9,12,7},3));
  }

}
