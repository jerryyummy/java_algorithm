import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Leetcode1443 {
  public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
    Map<Integer, List<Integer>> map = new HashMap<>();
    for (int[] edge : edges) {
      int node1 = edge[0];
      int node2 = edge[1];

      map.putIfAbsent(node1, new ArrayList<>());
      map.putIfAbsent(node2, new ArrayList<>());
      map.get(node1).add(node2);
      map.get(node2).add(node1);
    }

    return dfs(0, -1, map, hasApple);
  }

  private int dfs(int node, int parent, Map<Integer, List<Integer>> map, List<Boolean> hasApple) {
    if (!map.containsKey(node)) return 0;

    int totalTime = 0;
    for (int child : map.get(node)) {
      if (child == parent) continue;

      int childTime = dfs(child, node, map, hasApple);
      if (childTime > 0 || hasApple.get(child)) {
        totalTime += childTime + 2;
      }
    }
    return totalTime;
  }
}
