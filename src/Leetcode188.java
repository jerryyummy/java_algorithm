public class Leetcode188 {
  public int maxProfit(int k, int[] prices) {
    int[][] f = new int[k + 2][2];
    for (int j = 1; j <= k + 1; j++) {
      f[j][1] = Integer.MIN_VALUE / 2; // 防止溢出
    }
    f[0][0] = Integer.MIN_VALUE / 2;
    for (int p : prices) {
      for (int j = k + 1; j > 0; j--) {
        f[j][0] = Math.max(f[j][0], f[j][1] + p);
        f[j][1] = Math.max(f[j][1], f[j - 1][0] - p);
      }
    }
    return f[k + 1][0];
  }
}
