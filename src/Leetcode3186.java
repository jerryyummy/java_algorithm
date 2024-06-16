import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Leetcode3186 {
  public long maximumTotalDamage(int[] power) {
    TreeMap<Integer, Integer> count = new TreeMap<>();
    for (int p : power) {
      count.merge(p, 1, Integer::sum);
    }

    Map<Integer, Long> cache = new HashMap<>();

    return dfs(count.firstKey(), count, cache);
  }

  private long dfs(Integer power, TreeMap<Integer, Integer> count, Map<Integer, Long> cache) {
    if (null == power) {
      return 0;
    }

    if (cache.containsKey(power)) {
      return cache.get(power);
    }

    // pick the current power
    long pick = (long) power * count.get(power) +
        // at least power + 3
        dfs(count.ceilingKey(power + 3), count, cache);

    // unpick the current power
    long unpick =
        dfs(
            // at least power + 1
            count.ceilingKey(power + 1), count, cache);

    long best = Math.max(pick, unpick);
    cache.put(power, best);

    return best;
  }

  /*
  public long maximumTotalDamage(int[] power) {
        TreeMap<Integer, Integer> count = new TreeMap<>();
        for (int p : power) {
            count.merge(p, 1, Integer::sum);
        }

        // Get all unique power values
        List<Integer> uniquePowers = new ArrayList<>(count.keySet());
        int n = uniquePowers.size();

        // DP array to store the maximum damage up to each unique power index
        long[] dp = new long[n + 1];

        // Fill the DP array
        for (int i = n - 1; i >= 0; i--) {
            int currentPower = uniquePowers.get(i);
            long pick = (long) currentPower * count.get(currentPower);
            int nextIndex = findNextIndex(uniquePowers, i + 1, currentPower + 3);

            if (nextIndex < n) {
                pick += dp[nextIndex];
            }

            long unpick = dp[i + 1];
            dp[i] = Math.max(pick, unpick);
        }

        return dp[0];
    }

    private int findNextIndex(List<Integer> uniquePowers, int startIndex, int target) {
        int left = startIndex;
        int right = uniquePowers.size();

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (uniquePowers.get(mid) >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
   */
}
