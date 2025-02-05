package tt;

public class MaximumPositiveFeedback {
  public static int getMaxPositiveFeedback(String videoFeedback) {
    int n = videoFeedback.length();
    int ones = 0;
    for (int i = 0; i < n; i++) {
      if (videoFeedback.charAt(i) == '1') {
        ones++;
      }
    }
    if (ones == 0) {
      return n;
    }
    int[] B = new int[n];
    int tt = 0;
    for (int i = 0; i < n; i++) {
      char ch = videoFeedback.charAt(i);
      if (ch == '0') {
        B[i] = 1;
      } else {
        B[i] = -1;
      }
      tt += B[i];
    }
    if (n <= 2) {
      return ones + tt;
    }
    int[] inter = new int[n - 2];
    for (int i = 1; i < n - 1; i++) {
      inter[i - 1] = B[i];
    }

    int s = fun(inter);
    return ones + tt - s;
  }

  private static int fun(int[] arr) {
    int cur = arr[0];
    int ans = arr[0];
    for (int i = 1; i < arr.length; i++) {
      cur = Math.min(arr[i], cur + arr[i]);
      ans = Math.min(ans, cur);
    }
    return ans;
  }
}
