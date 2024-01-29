package snowflake;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class MaximumOrderVolume {
  public static int maximumOrderVolume(int[] start, int[] duration, int[] volume) {
    int[][] calls = new int[start.length][3];
    for (int i = 0; i < start.length; i++) {
      calls[i] = new int[]{start[i], start[i] + duration[i], volume[i]};
    }
    // Sort calls by end time
    Arrays.sort(calls, (a, b) -> a[1] - b[1]);

    // TreeMap to store the maximum volume at each end time
    TreeMap<Integer, Integer> dp = new TreeMap<>();
    dp.put(0, 0); // Initial value

    for (int[] call : calls) {
      // Lower Entry gives the greatest key strictly less than the given key
      Map.Entry<Integer, Integer> entry = dp.lowerEntry(call[0]);
      int maxVolumeUntilNow = entry != null ? entry.getValue() : 0;
      int newVolume = maxVolumeUntilNow + call[2];

      // Check if we should update the max volume at the end time of the current call
      entry = dp.floorEntry(call[1]);
      if (entry == null || entry.getValue() < newVolume) {
        dp.put(call[1], newVolume);
        // 移除所有在当前电话结束时间之后且订单量不大于 newVolume 的条目
        while ((entry = dp.higherEntry(call[1])) != null && entry.getValue() <= newVolume) {
          dp.remove(entry.getKey());
        }
      }
    }

    return dp.lastEntry().getValue();
  }

}
