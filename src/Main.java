import java.util.*;

public class Main {
  public int maximumLength(int[] nums) {
    for (int i = 0; i < nums.length; i++) {
      nums[i] = nums[i]%2;
    }

    int a = 0;
    int b = 0;
    for(int num : nums) {
      if(num == 0) a++;
      else if(num == 1) b++;
    }
    return Math.max(a,b);
  }

  public static void main(String[] args) {
    Main main = new Main();
    System.out.println(main.minimumDiameterAfterMerge(new int[][]{{0,1},{0,2},{0,3}}, new int[][]{{0,1},{0,2},{0,3}}));
  }

  public int minimumDiameterAfterMerge(int[][] edges1, int[][] edges2) {
    int m = findTreeHeight(edges1);
    int n = findTreeHeight(edges2);
    return m+n+1;
  }

  public int findTreeHeight(int[][] edges1) {
    if (edges1 == null || edges1.length == 0) {
      return 0;
    }

    int n = edges1.length + 1;
    UnionFind uf = new UnionFind(n);
    List<List<Integer>> adjacencyList = new ArrayList<>(n);

    for (int i = 0; i < n; i++) {
      adjacencyList.add(new ArrayList<>());
    }

    // 构建邻接表和度数数组
    for (int[] edge : edges1) {
      int u = edge[0];
      int v = edge[1];
      adjacencyList.get(u).add(v);
      adjacencyList.get(v).add(u);
      uf.union(v, u);
    }

    int root = uf.find(0);

    boolean[] visited = new boolean[n];
    return dfs(root, adjacencyList, visited);
  }

  private int dfs(int node, List<List<Integer>> adjacencyList, boolean[] visited) {
    visited[node] = true;
    int height = 0;

    for (int neighbor : adjacencyList.get(node)) {
      if (!visited[neighbor]) {
        height = Math.max(height, dfs(neighbor, adjacencyList, visited));
      }
    }

    return height + 1;
  }

}

