import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Leetcode1615 {
//  public int maximalNetworkRank(int n, int[][] roads) {
//    Map<Integer, Set<Integer>> map = new HashMap<>();
//    for (int[] road : roads) {
//      Set<Integer> set1 = map.getOrDefault(road[0], new HashSet<>());
//      set1.add(road[1]);
//      map.put(road[0], set1);
//      Set<Integer> set2 = map.getOrDefault(road[1], new HashSet<>());
//      set2.add(road[0]);
//      map.put(road[1], set2);
//    }
//
//    int res = 0;
//    for (int i = 0; i < n-1; i++) {
//      for (int j = i+1; j < n; j++) {
//        int temp = map.getOrDefault(i, new HashSet<>()).size();
//        temp+=map.getOrDefault(j, new HashSet<>()).size();
//        if(map.getOrDefault(i, new HashSet<>()).contains(j)) temp--;
//        res = Math.max(res, temp);
//      }
//    }
//    return res;
//  }

  public int maximalNetworkRank(int n, int[][] roads) {
    boolean[][] connect = new boolean[n][n];
    int[] degree = new int[n];
    for (int[] road : roads) {
      int x = road[0], y = road[1];
      connect[x][y] = true;
      connect[y][x] = true;
      degree[x]++;
      degree[y]++;
    }

    int first = -1, second = -2;
    List<Integer> firstArr = new ArrayList<Integer>();
    List<Integer> secondArr = new ArrayList<Integer>();
    for (int i = 0; i < n; ++i) {
      if (degree[i] > first) {
        second = first;
        secondArr = new ArrayList<Integer>(firstArr);
        first = degree[i];
        firstArr.clear();
        firstArr.add(i);
      } else if (degree[i] == first) {
        firstArr.add(i);
      } else if (degree[i] > second){
        secondArr.clear();
        second = degree[i];
        secondArr.add(i);
      } else if (degree[i] == second) {
        secondArr.add(i);
      }
    }

    if (firstArr.size() == 1) {
      int u = firstArr.get(0);
      for (int v : secondArr) {
        if (!connect[u][v]) {
          return first + second;
        }
      }
      return first + second - 1;
    } else {
      int m = roads.length;
      if (firstArr.size() * (firstArr.size() - 1) / 2 > m) {
        return first * 2;
      }
      for (int u : firstArr) {
        for (int v : firstArr) {
          if (u != v && !connect[u][v]) {
            return first * 2;
          }
        }
      }
      return first * 2 - 1;
    }
  }
}
