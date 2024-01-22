package snowflake;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RadioWaves {
  public int calculateMax(int network_nodes, int[] network_from, int[] network_to, int[] frequency){
    HashMap<Integer, List<Integer>> map = new HashMap<>();
    for (int i = 0; i < network_nodes-1; i++) {
      map.putIfAbsent(network_from[i],new ArrayList<>());
      map.get(network_from[i]).add(network_to[i]);
      map.putIfAbsent(network_to[i],new ArrayList<>());
      map.get(network_to[i]).add(network_from[i]);
    }
    int res = -1;
    for (int i = 1; i <= network_nodes; i++) {
      res = Math.max(res,bfs(network_nodes,i,map,frequency));
    }
    return res;
  }

  public int bfs(int network_nodes, int from, HashMap<Integer, List<Integer>> map, int[] frequency){
    boolean[] visited = new boolean[network_nodes];
    visited[from-1] = true;
    Queue<Integer> queue = new LinkedList<>();
    queue.add(from);
    int step = 0;
    while (!queue.isEmpty()){
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        int node = queue.remove();
        for(int neighbor:map.get(node)){
          if(Math.abs(frequency[node-1]-frequency[neighbor-1])<=1 && !visited[neighbor-1]){
            visited[neighbor-1] = true;
            queue.add(neighbor);
          }
        }
      }
      step++;
    }
    return step==1?-1:step-1;
  }

  public static void main(String[] args) {
    RadioWaves solution = new RadioWaves();
    System.out.println(solution.calculateMax(5, new int[]{1,2,3,1}, new int[]{2,3,4,5}, new int[]{3,2,3,2,2}));
  }

}
