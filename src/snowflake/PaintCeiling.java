package snowflake;

import java.util.Arrays;

public class PaintCeiling {
  public static long variantsCount(int n, int s0, int k, int b, int m, long a) {
    long[] s = new long[n];
    s[0] = s0;

    for (int i = 1; i < n; i++) {
      s[i] = ((k * s[i - 1] + b) % m) + 1 + s[i - 1];
    }


    Arrays.sort(s);

    long count = 0;

    for (int i = 0; i < n; i++) {
      int left = 0;
      int right = n - 1;
      int best = 0;

      while (left <= right) {
        int mid = left + (right - left) / 2;


        if (s[i] * s[mid] <= a) {
          best = mid;
          left = mid + 1;
        } else {
          right = mid - 1;
        }
      }


      count += (best + 1);
    }

    return count;
  }

  public static void main(String[] args) {
    System.out.println(variantsCount(3,1,1,1,2,4));
  }
}
