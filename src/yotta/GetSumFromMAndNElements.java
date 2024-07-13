package yotta;
import java.util.*;

public class GetSumFromMAndNElements {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    // 读取数组A
    String lineA = scanner.nextLine();
    int[] A = Arrays.stream(lineA.split(" ")).mapToInt(Integer::parseInt).toArray();

    String lineB = scanner.nextLine();
    int[] B = Arrays.stream(lineB.split(" ")).mapToInt(Integer::parseInt).toArray();

    int m = scanner.nextInt();
    int n = scanner.nextInt();

    int maxSum = getMaxSum(A, B, m, n);
    System.out.println(maxSum);
  }

  public static int getMaxSum(int[] A, int[] B, int m, int n) {
    int N = A.length;

    int[][][] dp = new int[N + 1][m + 1][n + 1];

    for (int i = 1; i <= N; i++) {
      for (int j = 0; j <= m; j++) {
        for (int k = 0; k <= n; k++) {

          dp[i][j][k] = dp[i - 1][j][k];

          if (j > 0) {
            dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j - 1][k] + A[i - 1]);
          }

          if (k > 0) {
            dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j][k - 1] + B[i - 1]);
          }
        }
      }
    }

    return dp[N][m][n];
  }
}
