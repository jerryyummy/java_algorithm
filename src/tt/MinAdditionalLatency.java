package tt;

import java.util.List;

public class MinAdditionalLatency {
  public static int minAdditionalLatency(int n, List<Integer> latency) {
    int[] inc = new int[1];
    dfs(0, n, latency, inc);
    return inc[0];
  }

  private static int dfs(int i, int n, List<Integer> latency, int[] inc) {
    int l = 2 * i + 1;
    int r = 2 * i + 2;
    if (l >= n) {
      return 0;
    }
    int costL = dfs(l, n, latency, inc) + latency.get(l - 1);
    int costR = dfs(r, n, latency, inc) + latency.get(r - 1);
    int d = Math.max(costL, costR);
    if (costL < d) {
      int diff = d - costL;
      inc[0] += diff;
      latency.set(l - 1, latency.get(l - 1) + diff);
      costL = d;
    }
    if (costR < d) {
      int diff = d - costR;
      inc[0] += diff;
      latency.set(r - 1, latency.get(r - 1) + diff);
      costR = d;
    }
    return d;
  }
}
