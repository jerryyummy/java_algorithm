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

    /*
    N 是图中节点的总数。colors 数组用于存储每个节点的“颜色”（即属于哪个连通分量）。
    开始时，所有节点的颜色都设置为 -1，表示它们还没有被访问。C 用于计数不同的连通分量
     */
    int N = graph.length;
    int[] colors = new int[N];
    Arrays.fill(colors, -1);
    int C = 0;

    /*
    对图中的每个节点进行遍历。如果发现一个未着色的节点（即 colors[node] == -1），
    则通过深度优先搜索（DFS）对该节点及其连通的节点进行着色，每找到一个新的连通分量，C 自增
     */
    for (int node = 0; node < N; ++node)
      if (colors[node] == -1)
        dfs(graph, colors, node, C++);

    /*
    遍历 colors 数组，统计每个连通分量的大小（即每种颜色包含的节点数）。
     */
    int[] size = new int[C];
    for (int color: colors)
      size[color]++;

    /*
    对于初始感染的每个节点，增加其颜色在 colorCount 中的计数。这表示该连通分量中有多少个节点是初始感染的
     */
    int[] colorCount = new int[C];
    for (int node: initial)
      colorCount[colors[node]]++;

    /*
    遍历每个初始感染节点。如果它所在的连通分量中只有它一个初始感染节点，那么考虑移除它。
   在所有可移除的节点中，选择能使得最大数量的非初始感染节点免受影响的节点。
     如果有多个节点满足条件，则选择索引最小的节点。如果没有找到这样的节点，则选择初始感染节点中索引最小的一个
     */
    int ans = Integer.MAX_VALUE;
    for (int node: initial) {
      int c = colors[node];
      if (colorCount[c] == 1) {//如果连通分量有>=2个初始感染，无论移除哪个都没用
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
