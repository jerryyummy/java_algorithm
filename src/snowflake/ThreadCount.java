package snowflake;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class ThreadCount {
  public int[] getMinSumNodeValues(int serviceNodes, int[] serviceFrom, int[] serviceTo, int k, int[][] currentValues) {
    Map<Integer, List<Integer>> adjacencyList = new HashMap<>();
    for (int i = 0; i < serviceNodes - 1; ++i) {
      adjacencyList.computeIfAbsent(serviceFrom[i], x -> new ArrayList<>()).add(serviceTo[i]);
      adjacencyList.computeIfAbsent(serviceTo[i], x -> new ArrayList<>()).add(serviceFrom[i]);
    }

    // Create a map to store the known configurations
    Map<Integer, Integer> knownConfigurations = new HashMap<>();
    for (int i = 0; i < k; ++i) {
      knownConfigurations.put(currentValues[i][0], currentValues[i][1]);
    }

    // Initialize the result array with known configurations
    int[] result = new int[serviceNodes];
    Arrays.fill(result, 0);
    for (Map.Entry<Integer, Integer> config : knownConfigurations.entrySet()) {
      result[config.getKey() - 1] = config.getValue();
    }

    // Iterate over currentValues
    for (int i = 0; i < currentValues.length; ++i) {
      int node = currentValues[i][0];
      int value = currentValues[i][1];

      // BFS to propagate thread values through the tree
      Queue<Integer> bfsQueue = new LinkedList<>();
      bfsQueue.add(node);
      Set<Integer> visited = new HashSet<>();
      int distance = 0;

      while (!bfsQueue.isEmpty()) {
        int size = bfsQueue.size();
        for (int j = 0; j < size; j++) {
          int current = bfsQueue.poll();
          visited.add(current);

          distance++;
          int curMin = 0;
          if (value - distance > 0) {
            curMin = value - distance;
          } else {
            if (distance % 2 == 0) {
              curMin = 2;
            } else {
              curMin = 1;
            }
          }

          for (int neighbor : adjacencyList.get(current)) {
            if (visited.contains(neighbor)) {
              continue;
            }
            visited.add(neighbor);
            if (!knownConfigurations.containsKey(neighbor)) {
              bfsQueue.add(neighbor);
              result[neighbor - 1] = Math.max(result[neighbor - 1], curMin);
            }
          }
        }
      }
    }
    return result;
  }


  public static void main(String[] args) {
    ThreadCount solution = new ThreadCount();
    int[] temp = solution.getMinSumNodeValues(5, new int[]{1,2,3,4}, new int[]{2,3,4,5},2, new int[][]{{1,2},{5,3}});
    for (int num : temp) {
      System.out.println(num);
    }
  }
}
