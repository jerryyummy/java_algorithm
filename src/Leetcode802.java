import java.util.ArrayList;
import java.util.List;

public class Leetcode802 {
  public List<Integer> eventualSafeNodes(int[][] graph) {
    int n = graph.length;
    boolean[] visit = new boolean[n];
    boolean[] inStack = new boolean[n];

    for (int i = 0; i < n; i++) {
      dfs(i, graph, visit, inStack);
    }

    List<Integer> safeNodes = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      if (!inStack[i]) {
        safeNodes.add(i);
      }
    }

    return safeNodes;
  }

  public boolean dfs(int i, int[][] graph, boolean[] visit, boolean[] inStack) {
    if (inStack[i]) return true;
    if (visit[i]) return false;
    visit[i] = true;
    inStack[i] = true;
    for (int neighbor: graph[i]) {
      if (dfs(neighbor, graph, visit, inStack)) {
        return true;
      }
    }
    inStack[i] = false;
    return false;
  }
}
