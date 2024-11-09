package tt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GoodArrays {
  private static final int MOD = 1000000007;

  public static int countGoodArrays(List<Integer> arr) {
    int n = arr.size();
    int minVal = Integer.MAX_VALUE;
    int maxVal = Integer.MIN_VALUE;

    for (int num : arr) {
      if (num != 0) {
        minVal = Math.min(minVal, num);
        maxVal = Math.max(maxVal, num);
      }
    }

    if (minVal == Integer.MAX_VALUE) return 0;
    if (maxVal - minVal > n - 1) return 0;

    int overallMin = minVal - (n - 1);
    int overallMax = maxVal + (n - 1);
    int offset = -overallMin;
    int dpSize = overallMax - overallMin + 1;

    int[] previous = new int[dpSize];
    int[] current = new int[dpSize];

    if (arr.get(0) == 0) {
      Arrays.fill(previous, 1);
    } else {
      int mappedVal = arr.get(0) + offset;
      if (mappedVal >= 0 && mappedVal < dpSize) {
        previous[mappedVal] = 1;
      } else {
        return 0;
      }
    }

    for (int i = 1; i < n; i++) {
      Arrays.fill(current, 0);
      if (arr.get(i) == 0) {
        for (int v = 0; v < dpSize; v++) {
          if (previous[v] > 0) {
            for (int change = -1; change <= 1; change++) {
              int newV = v + change;
              if (newV >= 0 && newV < dpSize) {
                current[newV] = (current[newV] + previous[v]) % MOD;
              }
            }
          }
        }
      } else {
        int currentValue = arr.get(i) + offset;
        if (currentValue < 0 || currentValue >= dpSize) return 0;
        int total = 0;
        for (int prev : new int[]{currentValue - 1, currentValue, currentValue + 1}) {
          if (prev >= 0 && prev < dpSize) {
            total = (total + previous[prev]) % MOD;
          }
        }
        current[currentValue] = total;
      }
      int[] temp = previous;
      previous = current;
      current = temp;
    }

    int result = 0;
    if (arr.get(n - 1) == 0) {
      for (int val : previous) {
        result = (result + val) % MOD;
      }
    } else {
      int mappedVal = arr.get(n - 1) + offset;
      if (mappedVal >= 0 && mappedVal < dpSize) {
        result = previous[mappedVal] % MOD;
      } else {
        return 0;
      }
    }

    return result;
  }

  public static void main(String[] args) {
    System.out.println(countGoodArrays(new ArrayList<Integer>(Arrays.asList(1,0,0,2))));
  }
}
