public class Leetcode3405 {
  private static final int MOD = 1_000_000_007;
  private static final int MX = 100_000;

  private static final long[] fac = new long[MX]; // fac[i] = i!
  private static final long[] invF = new long[MX]; // invF[i] = i!^-1

  static {
    fac[0] = 1;
    for (int i = 1; i < MX; i++) {
      fac[i] = fac[i - 1] * i % MOD;
    }

    invF[MX - 1] = pow(fac[MX - 1], MOD - 2);
    for (int i = MX - 1; i > 0; i--) {
      invF[i - 1] = invF[i] * i % MOD;
    }
  }

  private static long pow(long x, int n) {
    long res = 1;
    for (; n > 0; n /= 2) {
      if (n % 2 > 0) {
        res = res * x % MOD;
      }
      x = x * x % MOD;
    }
    return res;
  }

  private long comb(int n, int m) {
    return fac[n] * invF[m] % MOD * invF[n - m] % MOD;
  }

  public int countGoodArrays(int n, int m, int k) {
    return (int) (comb(n - 1, k) * m % MOD * pow(m - 1, n - k - 1) % MOD);
  }
}
