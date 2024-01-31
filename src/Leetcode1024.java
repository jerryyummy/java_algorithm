import java.util.Arrays;
import java.util.Comparator;

public class Leetcode1024 {
//  public int videoStitching(int[][] clips, int time) {
//    int res = 0;
//    Arrays.sort(clips, new Comparator<int[]>() {
//      public int compare(int[] a, int[] b) { return a[0] - b[0]; }
//    });
//    for (int i = 0, start = 0, end = 0; start < time; start = end) {
//      for (; i < clips.length && clips[i][0] <= start; ++i)
//        end = Math.max(end, clips[i][1]);
//      if (start == end) return -1;
//      ++res;
//    }
//    return res;
//  }

  public int videoStitching(int[][] clips, int T) {
    int[] dp = new int[T + 1];
    Arrays.fill(dp, T + 1);
    dp[0] = 0;
    for (int i = 1; i <= T && dp[i - 1] < T; i++) {
      for (int[] c : clips) {
        if (c[0] <= i && i <= c[1])
          dp[i] = Math.min(dp[i], dp[c[0]] + 1);
      }
    }
    return dp[T] == T + 1 ? -1 : dp[T];
  }
}
