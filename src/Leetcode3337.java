import java.util.List;

public class Leetcode3337 {
  private static final int MOD = 1_000_000_007;
  private static final int SIZE = 26;

  public int lengthAfterTransformations(String s, int t, List<Integer> nums) {
    //快速幂矩阵
    int[][] m = new int[SIZE][SIZE];
    for (int i = 0; i < SIZE; i++) {
      int c = nums.get(i);
      for (int j = i + 1; j <= i + c; j++) {
        m[i][j % SIZE] = 1;
      }
    }
    m = pow(m, t);

    int[] cnt = new int[SIZE];
    for (char c : s.toCharArray()) {
      cnt[c - 'a']++;
    }

    long ans = 0;
    for (int i = 0; i < SIZE; i++) {
      // m 第 i 行的和就是 f[t][i]
      long fti = 0;
      for (int x : m[i]) {
        fti += x;
      }
      ans += fti * cnt[i];
    }
    return (int) (ans % MOD);
  }

  // 返回 n 个矩阵 a 相乘的结果
  private int[][] pow(int[][] a, int n) {
    int[][] res = new int[SIZE][SIZE];
    for (int i = 0; i < SIZE; i++) {
      res[i][i] = 1; // 单位矩阵
    }
    while (n > 0) {
      if ((n & 1) > 0) {
        res = mul(res, a);
      }
      a = mul(a, a);
      n >>= 1;
    }
    return res;
  }

  // 返回矩阵 a 和矩阵 b 相乘的结果
  private int[][] mul(int[][] a, int[][] b) {
    int[][] c = new int[SIZE][SIZE];
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        for (int k = 0; k < SIZE; k++) {
          c[i][j] = (int) ((c[i][j] + (long) a[i][k] * b[k][j]) % MOD);
        }
      }
    }
    return c;
  }
}
