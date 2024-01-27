import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Leetcode2421 {

  public int numberOfGoodPaths(int[] vals, int[][] edges) {
    Map<Integer, List<Integer>> adj = new HashMap<>();
    for (int[] edge : edges) {
      int a = edge[0], b = edge[1];
      adj.computeIfAbsent(a, value -> new ArrayList<Integer>()).add(b);
      adj.computeIfAbsent(b, value -> new ArrayList<Integer>()).add(a);
    }

    int n = vals.length;
    // Mapping from value to all the nodes having the same value in sorted order of
    // values.
    TreeMap<Integer, List<Integer>> valuesToNodes = new TreeMap<>();
    for (int i = 0; i < n; i++) {
      valuesToNodes.computeIfAbsent(vals[i], value -> new ArrayList<Integer>()).add(i);
    }

    UnionFind dsu = new UnionFind(n);
    int goodPaths = 0;

    // Iterate over all t nheodes with the same value in sorted order, starting from
    // the lowest value.
    for (int value : valuesToNodes.keySet()) {
      // For every node in nodes, combine the sets of the node and its neighbors into
      // one set.
      for (int node : valuesToNodes.get(value)) {
        if (!adj.containsKey(node))//是独立节点，不用考虑
          continue;
        for (int neighbor : adj.get(node)) {
          // Only choose neighbors with a smaller value, as there is no point in
          // traversing to other neighbors.
          if (vals[node] >= vals[neighbor]) {
            dsu.union(node, neighbor);
          }
        }
      }
      // Map to compute the number of nodes under observation (with the same values)
      // in each of the sets.
      Map<Integer, Integer> group = new HashMap<>();
      // Iterate over all the nodes. Get the set of each node and increase the count
      // of the set by 1.
      for (int u : valuesToNodes.get(value)) {
        group.put(dsu.find(u), group.getOrDefault(dsu.find(u), 0) + 1);
      }
      // For each set of "size", add size * (size + 1) / 2 to the number of goodPaths.
      for (int key : group.keySet()) {
        int size = group.get(key);
        goodPaths += size * (size + 1) / 2;
      }
    }
    return goodPaths;
  }

}
