package tt;

import java.util.*;

public class MaxGoodSubArrayLength {
  public static int getMaxSubArrayLength(int limit, List<Integer> financialMetrics) {
    int n = financialMetrics.size();
    int[] left = new int[n];
    int[] right = new int[n];
    Stack<Integer> s = new Stack<>();

    for (int i = 0; i < n; i++) {
      while (!s.isEmpty() && financialMetrics.get(s.peek()) >= financialMetrics.get(i)) {
        s.pop();
      }
      left[i] = s.isEmpty() ? 0 : s.peek() + 1;
      s.push(i);
    }

    s.clear();

    for (int i = n - 1; i >= 0; i--) {
      while (!s.isEmpty() && financialMetrics.get(s.peek()) >= financialMetrics.get(i)) {
        s.pop();
      }
      right[i] = s.isEmpty() ? n - 1 : s.peek() - 1;
      s.push(i);
    }

    int maxLength = -1;

    for (int i = 0; i < n; i++) {
      int len = right[i] - left[i] + 1;
      long te = (long) financialMetrics.get(i) * (long) len;
      if (te > (long) limit) {
        maxLength = Math.max(maxLength, len);
      }
    }

    return maxLength;
  }

  public static void main(String[] args) {
    ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(1, 3, 4, 3, 1));
    int limit = 6;
    int result = getMaxSubArrayLength(limit, arr);
    System.out.println(result);
  }
}
