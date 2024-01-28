import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Leetcode743 {
  public int networkDelayTime(int[][] times, int n, int k) {
    int[] minDistance = new int[n];
    Arrays.fill(minDistance, Integer.MAX_VALUE);

    HashMap<Integer, List<int[]>> adj = new HashMap<>();
    for (int i = 0; i < n; i++) {
      adj.put(i + 1, new ArrayList<>());
    }

    for (int[] time : times) {
      adj.get(time[0]).add(new int[]{time[1], time[2]});
    }

    boolean[] processed = new boolean[n]; // 新增布尔数组来标记节点是否已处理
    minDistance[k - 1] = 0;
    Queue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
    queue.add(new int[]{k, 0});

    while (!queue.isEmpty()) {
      int[] cur = queue.remove();
      int node = cur[0];

      if (processed[node - 1]) continue; // 跳过已处理的节点
      processed[node - 1] = true; // 标记当前节点为已处理

      for (int[] neighbor : adj.get(node)) {
        if (minDistance[node - 1] + neighbor[1] < minDistance[neighbor[0] - 1]) {
          minDistance[neighbor[0] - 1] = minDistance[node - 1] + neighbor[1];
          queue.add(new int[]{neighbor[0], minDistance[neighbor[0] - 1]});
        }
      }
    }

    int res = 0;
    for (int distance : minDistance) {
      res = Math.max(res, distance);
    }
    return res == Integer.MAX_VALUE ? -1 : res;
  }

}
