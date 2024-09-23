package tt;

import java.util.List;

public class ReelsViewing {
  public static long optimizeTiktokWatchTime(long m, List<Integer> initialWatch, List<Integer> repeatWatch) {
    long ans = Long.MAX_VALUE;
    int n = initialWatch.size();
    long pre = 0;

    for (long i = 0; i < n; i++) {
      if (i >= m) break;
      pre += initialWatch.get((int) i) + repeatWatch.get((int) i);
      ans = Math.min(ans, pre + (m - i - 1) * repeatWatch.get((int) i));
    }

    return ans;
  }
}
