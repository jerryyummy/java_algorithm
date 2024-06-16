import java.util.Arrays;
import java.util.HashMap;

public class Main {
  public long maximumTotalDamage(int[] power) {
    int n = power.length;
    long[][] dp = new long[n+1][2];

    Arrays.sort(power);

    dp[1][0] = power[0];

    for (int i = 1; i < n; i++) {
      dp[i+1][0] = power[i];
      int last = -1;
      for (int j = i - 1; j >=0; j--) {
        if (power[j] < power[i]-2) {
          last = j;
          break;
        }
      }
      if (power[i-1] == power[i]){
        dp[i+1][0] += dp[i][0];
        dp[i+1][1] = dp[last+1][0];
      }else{
        dp[i+1][0] += dp[last+1][0];
        dp[i+1][1] = dp[last+1][0];
      }
    }

    long res = 0;
    for (int i = 1; i <= n; i++) {
      res = Math.max(res, dp[i][0]);
      res = Math.max(res, dp[i][1]);
    }
    return res;
  }

  // Example usage
  public static void main(String[] args) {
    int[] power = {5,9,2,10,2,7,10,9,3,8};
    System.out.println(new Main().maximumTotalDamage(power)); // Example output
  }
}

