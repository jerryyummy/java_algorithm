import java.util.*;

public class Leetcode815 {
  public int numBusesToDestination(int[][] routes, int source, int target){
    if(source==target) return 0;
    HashMap<Integer, List<Integer>> map = new HashMap<>();
    for (int i = 0; i < routes.length; i++) {
      for (int j = 0; j < routes[i].length; j++) {
        List<Integer> list = map.getOrDefault(routes[i][j],new ArrayList<>());
        list.add(i);
        map.put(routes[i][j],list);
      }
    }
    Queue<Integer> queue = new LinkedList<>();
    Set<Integer> visited = new HashSet<>();
    for (int route:map.get(source)){
      for (int stop:routes[route]){
        queue.add(stop);
      }
      visited.add(route);
    }

    int res = 1;
    while(!queue.isEmpty()){
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        int curr = queue.remove();
        if(curr==target) return res;
        for (int route:map.get(curr)){
          if (!visited.contains(route)){
            for(int stop:routes[route]){
              queue.add(stop);
            }
            visited.add(route);
          }
        }
      }
      res++;
    }
    return -1;
  }

}
