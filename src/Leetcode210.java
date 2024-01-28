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

}
