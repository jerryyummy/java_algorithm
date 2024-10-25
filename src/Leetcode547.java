import java.util.HashSet;
import java.util.Set;

class UnionFind1 {
  int[] parent;
  int[] rank;

  public UnionFind1(int n) {
    this.parent = new int[n];
    this.rank = new int[n];
    for (int i = 0; i < n; i++) {
      parent[i] = i;
    }
  }

  // Path compression for optimization
  public int find(int n) {
    if (parent[n] != n) {
      parent[n] = find(parent[n]); // Recursively find the root and compress the path
    }
    return parent[n];
  }

  public boolean union(int a, int b) {
    int rootA = find(a);
    int rootB = find(b);

    if (rootA == rootB) {
      return false; // a and b are already in the same set
    }

    // Union by rank to keep the tree flat
    if (rank[rootA] > rank[rootB]) {
      parent[rootB] = rootA;
    } else if (rank[rootA] < rank[rootB]) {
      parent[rootA] = rootB;
    } else {
      parent[rootB] = rootA;
      rank[rootA] += 1;
    }
    return true;
  }
}

class Leetcode547 {
  public int findCircleNum(int[][] isConnected) {
    int n = isConnected.length;
    UnionFind1 uf = new UnionFind1(n);

    // Union all connected cities
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (isConnected[i][j] == 1) {
          uf.union(i, j);
        }
      }
    }

    // Count unique root nodes (distinct provinces)
    Set<Integer> set = new HashSet<>();
    for (int i = 0; i < n; i++) {
      set.add(uf.find(i));
    }

    return set.size();
  }
}
