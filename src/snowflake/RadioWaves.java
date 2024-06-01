package snowflake;

import java.util.*;

//经典的树上俩点最长距离的问题，就是多加了一个constraint，
// 一遍dfs即可把通过任意节点的最长路径全算出来，其中的最大值就是结果了。线性时间复杂度
public class RadioWaves {
  HashMap<Integer, List<Integer>> graph = new HashMap<>();
  int res = 0;

  private void buildGraph(int network_nodes, int[] network_from, int[] network_to) {
    for (int i = 0; i < network_nodes-1; i++) {
      graph.computeIfAbsent(network_from[i], k -> new ArrayList<>()).add(network_to[i]);
      graph.computeIfAbsent(network_to[i], k -> new ArrayList<>()).add(network_from[i]);
    }
  }

  public int calculateMax(int network_nodes, int[] network_from, int[] network_to, int[] frequency) {
     buildGraph(network_nodes, network_from, network_to);
     dfs(1, frequency, 0);
     return res;
  }

  private int dfs(int node, int[] frequency, int parent) {
    int first = 0;
    int second = 0;
    for (int next:graph.get(node)){
      if (next != parent){
        if (Math.abs(frequency[next-1] - frequency[node-1]) > 1){
          dfs(next, frequency, node);
        }else{
          int temp = dfs(next, frequency, node);
          if(temp>first){
            second = first;
            first = temp;
          }else if(temp > second){
            second = temp;
          }
        }
      }
    }
    res = Math.max(res, first+second);
    return first+1;
  }

  public static void main(String[] args) {
    RadioWaves solution = new RadioWaves();
    int longestDistance = solution.calculateMax(10, new int[]{1, 1, 1, 2, 3, 5, 5, 7, 8}, new int[]{6, 2, 3, 5, 4, 7, 8, 9, 10}, new int[]{1, 3, 2, 3, 2, 2, 1, 1, 2, 2});
    System.out.println("The longest distance is: " + longestDistance);
  }
}

