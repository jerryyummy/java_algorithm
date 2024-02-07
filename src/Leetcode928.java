import java.util.Arrays;

public class Leetcode928 {
  private int dfs(int[][] graph, int u, boolean[] visited, boolean[] infected) {
    if(infected[u]) return 0;//返回0，表示当前路径会导致感染传播
    visited[u] = true;
    int count = 1;
    for(int v = 0; v < graph[u].length; v++) {
      if(!visited[v] && graph[u][v] == 1) {
        int c = dfs(graph, v, visited, infected);
        if(c == 0) {
          infected[u] = true;
          return 0;
        }
        count += c;
      }
    }
    return count;
  }
  public int minMalwareSpread(int[][] graph, int[] initial) {
    int n = graph.length, ans = initial[0], max = 0;
    boolean[] infected = new boolean[n];
    for(int u: initial) infected[u] = true;
    for(int u: initial) {
      boolean[] visited = new boolean[n];
      visited[u] = true;
      int count = 0;
      for(int i = 0; i < n; i++) {
        if(!visited[i] && graph[u][i] == 1) {
          count += dfs(graph, i, visited, infected);
        }
      }
      if(count > max || count == max && u < ans) {
        max = count;
        ans = u;
      }
    }
    return ans;
  }

  /*
  public int minMalwareSpread(int[][] graph, int[] initial) {
        int n = graph.length, ans = initial[0], max = 0;
        boolean[] infected = new boolean[n];
        for(int u: initial) infected[u] = true;
        UnionFind uf = new UnionFind(n);
        for(int i = 0; i < n; i++) {
            if(!infected[i]) {
                for(int j = i + 1; j < n; j++) {
                    if(!infected[j] && graph[i][j] == 1) uf.union(i, j);//遍历图中的每个节点，如果两个节点都没有被感染并且在图中直接连接，则将它们合并到同一个集合中
                }
            }
        }
        int[] count = new int[n];
        Set<Integer>[] component = new Set[n];
        for(int u: initial) {
            component[u] = new HashSet<>();//存储每个最初感染的节点可以直接影响的集合
            for(int v = 0; v < n; v++) {
                if(!infected[v] && graph[u][v] == 1) component[u].add(uf.find(v));
            }
            for(int v: component[u]) count[v]++;//对每个这样的集合，增加它被感染源影响的次数
        }
        for(int u: initial) {
            int save = 0;
            for(int v: component[u]) {
                if(count[v] == 1) save += uf.size[v];
            }
            if(save > max || save == max && u < ans) {
                max = save;
                ans = u;
            }
        }
        return ans;
    }
   */

}
