import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Leetcode924 {
  public int minMalwareSpread(int[][] graph, int[] initial) {
    // Sort the initial infected list to ensure the smallest node is chosen in case of a tie
    Arrays.sort(initial);

    int N = graph.length;
    int[] colors = new int[N];
    Arrays.fill(colors, -1);
    int C = 0;

    // Color each component.
    for (int node = 0; node < N; ++node)
      if (colors[node] == -1)
        dfs(graph, colors, node, C++);

    // Size of each color.
    int[] size = new int[C];
    for (int color: colors)
      size[color]++;

    // Find the unique colors.
    int[] colorCount = new int[C];
    for (int node: initial)
      colorCount[colors[node]]++;

    // Answer
    int ans = Integer.MAX_VALUE;
    for (int node: initial) {
      int c = colors[node];
      if (colorCount[c] == 1) {
        // Choose the smallest node of the largest component.
        if (ans == Integer.MAX_VALUE)
          ans = node;
        else if (size[c] > size[colors[ans]])
          ans = node;
        else if (size[c] == size[colors[ans]] && node < ans)
          ans = node;
      }
    }

    if (ans == Integer.MAX_VALUE)
      for (int node: initial)
        ans = Math.min(ans, node);

    return ans;
  }

  public void dfs(int[][] graph, int[] colors, int node, int color) {
    colors[node] = color;
    for (int nei = 0; nei < graph.length; ++nei)
      if (graph[node][nei] == 1 && colors[nei] == -1)
        dfs(graph, colors, nei, color);
  }

  /*
  public int minMalwareSpread(int[][] graph, int[] initial) {
    HashMap<Integer, List<Integer>> adj = new HashMap<>();
    for (int i = 0; i < graph.length; i++) {
      for (int j = 0; j < graph[0].length; j++) {
        if (graph[i][j] == 1){
          adj.putIfAbsent(i,new ArrayList<>());
          adj.get(i).add(j);
        }
      }
    }

    int res = Integer.MAX_VALUE;
    int mask = 0;
    for(int node:initial){
      mask = mask | (1<<node);
    }
    Arrays.sort(initial);
    int removeNode = initial[0];
    for (int i = 0; i < initial.length; i++) {
      int initialNodes = mask ^ (1<< initial[i]);
      int temp = bfs(adj,initialNodes, graph.length);
      if (res>temp || res==temp && initial[i]<removeNode){
        res = temp;
        removeNode = initial[i];
      }
    }

    return removeNode;

  }

  private int bfs(HashMap<Integer, List<Integer>> adj, int mask, int n){
    HashSet<Integer> visited = new HashSet<>();
    Queue<Integer> queue = new LinkedList<>();
    int res = 0;
    for (int i = 0; i < n; i++) {
      if (((mask>>i) & 1) ==1){
        visited.add(i);
        queue.add(i);
        res++;
      }
    }

    while (!queue.isEmpty()){
      int node = queue.remove();
      for(int neighbor:adj.get(node)){
        if (!visited.contains(neighbor)){
          visited.add(neighbor);
          queue.add(neighbor);
          res++;
        }
      }
    }

    return res;
  }
   */
}
