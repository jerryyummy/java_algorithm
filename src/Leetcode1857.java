import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Leetcode1857 {
  public int largestPathValue(String colors, int[][] edges) {
    int[][] dp = new int[colors.length()][26];
    int[] indegree = new int[colors.length()];
    HashMap<Integer, List<Integer>> adjlist = new HashMap<>();
    for (int i = 0; i < colors.length(); i++) {
      adjlist.put(i,new ArrayList<>());
    }
    for (int[] edge:edges){
      adjlist.get(edge[0]).add(edge[1]);
      indegree[edge[1]]++;
    }
    Queue<Integer> queue = new LinkedList<>();
    int res = 0, nodeSeen = 0;
    for (int i = 0; i < colors.length(); i++) {
      if (indegree[i]==0){
        queue.add(i);
      }
    }
    while (!queue.isEmpty()){
      int node = queue.remove();
      res = Math.max(res,++dp[node][colors.charAt(node)-'a']);
      nodeSeen++;
      for (int neighbor: adjlist.get(node)){
        for (int i = 0; i < 26; i++) {
          dp[neighbor][i]=Math.max(dp[neighbor][i],dp[node][i]);
        }
        indegree[neighbor]--;
        if (indegree[neighbor]==0){
          queue.add(neighbor);
        }
      }
    }
    return nodeSeen<colors.length()?-1:res;
  }

}
