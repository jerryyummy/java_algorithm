package snowflake;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class ThreadCount {
  int[] res;
  int n;
  Map<Integer, List<Integer>> adj;
  Map<Integer, Integer> currValuesMap;
  int max = Integer.MAX_VALUE;

  public int[] getMinSumNodeValues(int service_nodes, int[] service_from, int[] service_to, int k, int[][] currentValues) {
    this.n = service_nodes;
    this.res = new int[n];
    this.currValuesMap = new HashMap<>();
    this.adj = new HashMap<>();
    int[] threads = new int[n];

    // Convert currentValues into a map for easy access
    for (int i = 0; i < k; i++) {
      currValuesMap.put(currentValues[i][0], currentValues[i][1]);
    }

    // Build adjacency list
    for (int i = 0; i < service_from.length; i++) {
      adj.computeIfAbsent(service_from[i], x -> new ArrayList<>()).add(service_to[i]);
      adj.computeIfAbsent(service_to[i], x -> new ArrayList<>()).add(service_from[i]);
    }

    // Start DFS from the first known configuration
    dfs(currentValues[0][0], currentValues[0][1], new HashSet<>(), currentValues[0][1], threads);

    return res;
  }

  public void dfs(int node, int val, HashSet<Integer> visited, int sum, int[] threads) {
    // Set the current node's value
    visited.add(node);
    threads[node-1] = val;
    if (visited.size()==n){
      if (sum<max) {
        max = sum;
        res = Arrays.copyOf(threads,n);
      }
    }

    // Visit all the neighbors
    for (int neighbor : adj.getOrDefault(node, new ArrayList<>())) {
      if (visited.contains(neighbor)) continue; // Skip the visited node

      // Calculate the value for the neighbor based on the current node's value
      if (currValuesMap.containsKey(neighbor) && Math.abs(val-currValuesMap.get(neighbor))==1){
        dfs(neighbor, currValuesMap.get(neighbor), visited, sum+val, threads);
      }
      if (!currValuesMap.containsKey(neighbor)){
        dfs(neighbor,val-1, visited, sum+val, threads);
        dfs(neighbor, val+1, visited, sum+val, threads);
      }
    }

    //end the loop
    visited.remove(node);
  }

  public static void main(String[] args) {
    ThreadCount solution = new ThreadCount();
    int[] temp = solution.getMinSumNodeValues(5, new int[]{1,2,3,4}, new int[]{2,3,4,5},2, new int[][]{{1,5},{5,7}});
    for (int num : temp) {
      System.out.println(num);
    }
  }
}
