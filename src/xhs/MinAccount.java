package xhs;

import java.util.Arrays;
import java.util.Scanner;

public class MinAccount {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int x = scanner.nextInt();
    int[] w = new int[n + 1];
    for (int i = 1; i <= n; i++) w[i] = scanner.nextInt();
    int[][][] f = new int[n + 1][x + 1][2];//账号个数，粉丝数，是否选择多次推荐
    for (int[][] arr : f) {
      for (int[] subArr : arr) {
        Arrays.fill(subArr, Integer.MAX_VALUE / 2);
      }
    }
    f[0][0][0] = 0;
    for (int i = 1; i <= n; i++) {
      for (int j = 0; j <= x; j++) {
        for (int k = 0; k < 2; k++) {
          f[i][j][k] = Math.min(f[i][j][k], f[i - 1][j][k]);//如果不选这个账号
          if (j >= w[i] / 2) {//只进行一次推广
            f[i][j][k] = Math.min(f[i][j][k], f[i - 1][j - w[i] / 2][k] + 1);
          }
          if (j >= w[i] && k > 0) {//该账号进行多次推广
            f[i][j][k] = Math.min(f[i][j][k], f[i - 1][j - w[i]][k - 1] + 1);
          }
        }
      }
    }
    int res = Math.min(f[n][x][0], f[n][x][1]);
    if (res == Integer.MAX_VALUE / 2) System.out.println("-1");
    else System.out.println(res);
  }
}
