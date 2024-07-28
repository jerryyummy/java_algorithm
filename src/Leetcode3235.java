import java.util.List;

public class Leetcode3235 {
  public boolean canReachCorner(int X, int Y, int[][] A) {
    int n = A.length;
    int[] f = new int[n + 2];

    for (int i = 0; i < n + 2; i++) {
      f[i] = i;
    }

    for (int i = 0; i < n; i++) {
      int x = A[i][0];
      int y = A[i][1];
      int r = A[i][2];

      if (x - r <= 0 || y + r >= Y) {
        f[find(f, n)] = find(f, i);
      }

      if (x + r >= X || y - r <= 0) {
        f[find(f, n + 1)] = find(f, i);
      }

      for (int j = 0; j < i; j++) {
        int x2 = A[j][0];
        int y2 = A[j][1];
        int r2 = A[j][2];

        if ((x - x2) * (x - x2) + (y - y2) * (y - y2) <= (r + r2) * (r + r2)) {
          f[find(f, i)] = find(f, j);
        }
      }
    }

    return find(f, n) != find(f, n + 1);
  }

  private int find(int[] f, int i) {
    if (f[i] != i) {
      f[i] = find(f, f[i]);
    }
    return f[i];
  }

}
