import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Leetcode1743 {
  public int[] restoreArray(int[][] adjacentPairs) {
    HashMap<Integer,Integer> map = new HashMap<>();
    HashMap<Integer,List<Integer>> neighbors = new HashMap<>();
    for (int[] adj:adjacentPairs) {
      map.put(adj[0], map.getOrDefault(adj[0], 0) + 1);
      map.put(adj[1], map.getOrDefault(adj[1], 0) + 1);
      neighbors.putIfAbsent(adj[0], new ArrayList<>());
      neighbors.putIfAbsent(adj[1], new ArrayList<>());
      neighbors.get(adj[0]).add(adj[1]);
      neighbors.get(adj[1]).add(adj[0]);
    }
      Queue<Integer> queue = new LinkedList<>();
      Set<Integer> visited = new HashSet<>();
      for (Map.Entry<Integer,Integer> entry:map.entrySet()){
        if (entry.getValue()==1){
          queue.add(entry.getKey());
          visited.add(entry.getKey());
          break;
        }
      }
      int[] res = new int[map.size()];
      int index = 0;
      while (!queue.isEmpty()){
        int num = queue.remove();
        res[index++] = num;
        if (index == map.size()) break;
        for (int neighbor:neighbors.get(num)){
          if (!visited.contains(neighbor)){
            visited.add(neighbor);
            queue.add(neighbor);
          }
        }
      }
    return res;
  }
}
