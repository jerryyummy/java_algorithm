import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Leetcode210 {
  public int[] findOrder(int numCourses, int[][] prerequisites) {
    int[] res = new int[numCourses];
    int flag = 0;
    HashMap<Integer,List<Integer>> adjust = new HashMap<>();
    HashMap<Integer,Integer> count = new HashMap<>();//计算入度
    for (int i = 0; i < numCourses; i++) {
      count.put(i,0);
      adjust.put(i,new ArrayList<>());
    }
    for (int[] require:prerequisites){
      count.put(require[0], count.get(require[0])+1);
      adjust.get(require[1]).add(require[0]);
    }
    Queue<Integer> queue = new LinkedList<>();
    for (int index:count.keySet()){
      if (count.get(index).equals(0)){
        queue.add(index);
        res[flag++] = index;
      }
    }
    while (!queue.isEmpty()){
      int index = queue.remove();
      for(int next: adjust.get(index)){
        count.put(next,count.get(next)-1);
        if (count.get(next).equals(0)){
          queue.add(next);
          res[flag++] = next;
        }
      }
    }
    if (flag!=numCourses) return new int[]{};
    return res;
  }

  /*
  // TODO a good implementation that combines directed cycle detection and topological sort
    List<Integer>[] adjacencyList;
    boolean hasCycle = false;
    boolean[] marked;
    boolean[] onStack;
    int[] order;
    int end;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        adjacencyList = new List[numCourses];
        marked = new boolean[numCourses];
        onStack = new boolean[numCourses];
        order = new int[numCourses];
        end = numCourses - 1;

        for (int[] prerequisite : prerequisites) {
            int src = prerequisite[1];
            int dst = prerequisite[0];
            if (adjacencyList[src] == null) adjacencyList[src] = new ArrayList<>();
            adjacencyList[src].add(dst);
        }
        for (int i = 0; i < adjacencyList.length; i++) {
            if (hasCycle) break;
            if (!marked[i]) topologicalSort(i);
        }

        if (hasCycle) return new int[0];
        else return order;
    }

    private void topologicalSort(int v) {
        marked[v] = true;
        if (adjacencyList[v] == null) {
            order[end--] = v;
            return;
        }
        onStack[v] = true;
        for (int w : adjacencyList[v]) {
            if (hasCycle) return;
            if (!marked[w]) {
                topologicalSort(w);
            } else if (onStack[w]) {
                hasCycle = true;
                return;
            }
        }
        onStack[v] = false;
        order[end--] = v;
    }
   */

}
