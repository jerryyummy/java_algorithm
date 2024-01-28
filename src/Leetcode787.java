import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Leetcode787 {
  public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
    /*
    Map<Integer, List<int[]>> adj = new HashMap<>();
    for (int[] i : flights)
      adj.computeIfAbsent(i[0], value -> new ArrayList<>()).add(new int[] { i[1], i[2] });

    int[] stops = new int[n];
    Arrays.fill(stops, Integer.MAX_VALUE);
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
    // {dist_from_src_node, node, number_of_stops_from_src_node}
    pq.offer(new int[] { 0, src, 0 });

    while (!pq.isEmpty()) {
      int[] temp = pq.poll();
      int dist = temp[0];
      int node = temp[1];
      int steps = temp[2];
      // We have already encountered a path with a lower cost and fewer stops,
      // or the number of stops exceeds the limit.
      if (steps > stops[node] || steps > k + 1)
        continue;
      stops[node] = steps;
      if (node == dst)
        return dist;
      if (!adj.containsKey(node))
        continue;
      for (int[] a : adj.get(node)) {
        pq.offer(new int[] { dist + a[1], a[0], steps + 1 });
      }
    }
    return -1;
     */

    // Distance from source to all other nodes.
    int[] dist = new int[n];
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[src] = 0;

    // Run only K+1 times since we want shortest distance in K hops
    for (int i = 0; i <= k; i++) {
      // Create a copy of dist vector.
      int[] temp = Arrays.copyOf(dist, n);
      for (int[] flight : flights) {
        if (dist[flight[0]] != Integer.MAX_VALUE) {
          temp[flight[1]] = Math.min(temp[flight[1]], dist[flight[0]] + flight[2]);
        }
      }
      // Copy the temp vector into dist.
      dist = temp;
    }
    return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
  }
}
