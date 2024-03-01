import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Leetcode207 {
  public boolean canFinish(int numCourses, int[][] prerequisites) {
    HashMap<Integer, List<Integer>> adjust = new HashMap<>();
    HashMap<Integer,Integer> count = new HashMap<>();
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
      }
    }
    while (!queue.isEmpty()){
      int index = queue.remove();
      for(int next: adjust.get(index)){
        count.put(next,count.get(next)-1);
        if (count.get(next).equals(0)){
          queue.add(next);
        }
      }
    }
    for (int index:count.keySet()){
      if (!count.get(index).equals(0)){
        return false;
      }
    }
    return true;
  }
}
