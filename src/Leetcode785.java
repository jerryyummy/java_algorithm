import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Leetcode785 {
  int[] colors;
  public boolean isBipartite(int[][] graph) {
    int n = graph.length;
//    int[] color = new int[n];
//    Arrays.fill(color, -1);
//
//    for (int start = 0; start < n; ++start) {
//      if (color[start] == -1) {
//        Stack<Integer> stack = new Stack();
//        stack.push(start);
//        color[start] = 0;
//
//        while (!stack.empty()) {
//          Integer node = stack.pop();
//          for (int neighbor: graph[node]) {
//            if (color[neighbor] == -1) {
//              stack.push(neighbor);
//              color[neighbor] = color[node] ^ 1;
//            } else if (color[neighbor] == color[node]) {
//              return false;
//            }
//          }
//        }
//      }
//    }
//    return true;

    colors = new int[n];

    // 遍历每个节点
    for (int i = 0; i < n; i++) {
      // 如果节点未着色，并且BFS返回false，则图不是二分图
      if (colors[i] == 0 && !bfs(graph, i)) {
        return false;
      }
    }
    return true;
  }

  private boolean bfs(int[][] graph, int src) {
    Queue<Integer> queue = new LinkedList<>();
    queue.add(src);
    colors[src] = 1; // 初始颜色

    while (!queue.isEmpty()) {
      int node = queue.poll();

      for (int neighbor : graph[node]) {
        if (colors[neighbor] == 0) {
          // 给邻居节点上与当前节点不同的颜色
          colors[neighbor] = -colors[node];
          queue.add(neighbor);
        } else if (colors[neighbor] == colors[node]) {
          // 如果邻居节点颜色与当前节点相同，则不是二分图
          return false;
        }
      }
    }
    return true;
  }

}
