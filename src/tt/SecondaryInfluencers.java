package tt;

import java.util.ArrayList;
import java.util.List;

public class SecondaryInfluencers {

  static final int N = 200005;
  static List<Integer>[] road = new ArrayList[N];
  static int[] f = new int[N];
  static int[] s = new int[N];

  static {
    for (int i = 0; i < N; i++) {
      road[i] = new ArrayList<>();
    }
  }

  public static void dfs1(int u, int fa) {
    for (int i = 0; i < road[u].size(); i++) {
      int v = road[u].get(i);
      if (v == fa) continue;
      dfs1(v, u);
      if (f[v] + 1 > f[u]) {
        s[u] = f[u];
        f[u] = f[v] + 1;
      } else if (f[v] + 1 > s[u]) {
        s[u] = f[v] + 1;
      }
    }
  }

  public static void dfs2(int u, int fa, int dis) {
    for (int i = 0; i < road[u].size(); i++) {
      int v = road[u].get(i);
      if (v == fa) continue;
      if (f[v] + 1 == f[u]) {
        dfs2(v, u, Math.max(dis + 1, s[u] + 1));
      } else {
        dfs2(v, u, Math.max(dis + 1, f[u] + 1));
      }
    }
    if (dis > f[u]) {
      s[u] = f[u];
      f[u] = dis;
    } else if (dis > s[u]) {
      s[u] = dis;
    }
  }

  public static long getSecondaryInfluencerSum(int g_nodes, List<Integer> g_from, List<Integer> g_to) {
    int m = g_from.size();
    for (int i = 0; i < m; i++) {
      int u = g_from.get(i);
      int v = g_to.get(i);
      road[u].add(v);
      road[v].add(u);
    }

    long maxn = 0;
    dfs1(1, -1);
    dfs2(1, -1, 0);

    for (int i = 1; i <= g_nodes; i++) {
      maxn = Math.max(maxn, f[i] + s[i]);
    }

    long sum = 0;
    for (int i = 1; i <= g_nodes; i++) {
      if (f[i] + s[i] != maxn) {
        sum += i;
      }
    }
    return sum;
  }

  public static void main(String[] args) {
    int n = 4;
    List<Integer> vec1 = List.of(1, 1, 2);
    List<Integer> vec2 = List.of(2, 3, 4);
    System.out.println(getSecondaryInfluencerSum(n, vec1, vec2));
  }
}

