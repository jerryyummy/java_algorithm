import java.util.HashSet;

public class Leetcode684 {
  public int[] findRedundantConnection(int[][] edges) {
    int n = edges.length;
    HashSet<Integer> visited = new HashSet<>();
    for(int[] edge:edges){
      if (visited.contains(edge[0]) && visited.contains(edge[1])){
        return edge;
      }else{
        visited.add(edge[0]);
        visited.add(edge[1]);
      }
    }
    return new int[]{0};
  }

}
