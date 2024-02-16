import java.util.Arrays;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

public class Leetcode1094 {
  public boolean carPooling(int[][] trips, int capacity) {
    Map<Integer, Integer> timestamp = new TreeMap<>();
    for (int[] trip : trips) {
      int startPassenger = timestamp.getOrDefault(trip[1], 0) + trip[0];
      timestamp.put(trip[1], startPassenger);

      int endPassenger = timestamp.getOrDefault(trip[2], 0) - trip[0];
      timestamp.put(trip[2], endPassenger);
    }
    int usedCapacity = 0;
    for (int passengerChange : timestamp.values()) {
      usedCapacity += passengerChange;
      if (usedCapacity > capacity) {
        return false;
      }
    }
    return true;
  }

  /*
  public boolean carPooling(int[][] trips, int capacity) {
        // 优先队列，按照时间点排序，如果时间相同，下车事件在前
        PriorityQueue<int[]> events = new PriorityQueue<>((a, b) -> {
            if (a[1] == b[1]) return a[2] - b[2];
            return a[1] - b[1];
        });

        // 添加所有上车和下车事件
        for (int[] trip : trips) {
            // 上车事件：[乘客数, 上车时间, 1]
            events.offer(new int[]{trip[0], trip[1], 1});
            // 下车事件：[乘客数, 下车时间, -1]
            events.offer(new int[]{trip[0], trip[2], -1});
        }

        int usedCapacity = 0;
        while (!events.isEmpty()) {
            int[] event = events.poll();
            // 根据事件类型更新车辆容量
            if (event[2] == 1) { // 上车
                usedCapacity += event[0];
            } else { // 下车
                usedCapacity -= event[0];
            }
            // 检查是否超载
            if (usedCapacity > capacity) {
                return false;
            }
        }

        return true;
    }
   */

}
