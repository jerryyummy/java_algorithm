import java.util.*;

public class Leetcode3203 {
  public int minimumDiameterAfterMerge(int[][] edges1, int[][] edges2) {
    return Math.max(height(edges1) + height(edges2) - 1, Math.max(dia(edges1), dia(edges2)));
  }

  public int height(int[][] edges) {
    int n = edges.length+1;
    ArrayList<ArrayList<Integer>> e = new ArrayList<>();
    for(int i = 0; i < n; i++) e.add(new ArrayList<>());
    int[] deg = new int[n];
    for(int[] x : edges) {
      e.get(x[0]).add(x[1]);
      e.get(x[1]).add(x[0]);
      deg[x[0]]++;
      deg[x[1]]++;
    }
    Queue<Integer> q = new LinkedList<>();
    for(int i = 0; i < n; i++) {
      if(deg[i] == 1) {
        q.add(i);
        deg[i]--;
      }
    }
    int d = 1;
    while(q.size()>1) {
      int size = q.size();
      while(size-->0) {
        int curr = q.remove();
        for(int x : e.get(curr)) {
          deg[x]--;
          if(deg[x] == 1) {
            q.add(x);
          }
        }
      }
      d++;
    }

    return d;
  }

  public int dia(int[][] edges) {
    int n = edges.length+1;
    ArrayList<ArrayList<Integer>> e = new ArrayList<>();
    for(int i = 0; i < n; i++) e.add(new ArrayList<>());
    for(int[] x : edges) {
      e.get(x[0]).add(x[1]);
      e.get(x[1]).add(x[0]);
    }
    Queue<Integer> q = new LinkedList<>();
    q.add(0);
    int node = 0;
    boolean[] seen = new boolean[n];
    seen[0] = true;
    while(q.size() > 0) {
      int size = q.size();
      while(size-->0) {
        int curr = q.remove();
        node = curr;
        for(int x : e.get(curr)) {
          if(!seen[x]) {
            seen[x] = true;
            q.add(x);
          }
        }
      }
    }
    q = new LinkedList<>();
    q.add(node);
    seen = new boolean[n];
    seen[node] = true;
    int d = 0;
    while(q.size() > 0) {
      int size = q.size();
      while(size-->0) {
        int curr = q.remove();
        node = curr;
        for(int x : e.get(curr)) {
          if(!seen[x]) {
            seen[x] = true;
            q.add(x);
          }
        }
      }
      d++;
    }
    return d-1;
  }
}
