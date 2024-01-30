package snowflake;

import java.util.*;

public class RadioWaves {
  private HashMap<Integer, List<Integer>> buildGraph(int network_nodes, int[] network_from, int[] network_to) {
    HashMap<Integer, List<Integer>> graph = new HashMap<>();
    for (int i = 0; i < network_from.length; i++) {
      graph.computeIfAbsent(network_from[i], k -> new ArrayList<>()).add(network_to[i]);
      graph.computeIfAbsent(network_to[i], k -> new ArrayList<>()).add(network_from[i]);
    }
    return graph;
  }

  // Pair class to keep track of node and its distance
  private static class Pair {
    int node;
    int dist;
    Pair(int node, int dist) {
      this.node = node;
      this.dist = dist;
    }
  }

  private Pair bfs(int startNode, HashMap<Integer, List<Integer>> graph, int[] frequency) {
    Queue<Integer> queue = new LinkedList<>();
    boolean[] visited = new boolean[frequency.length + 1];
    queue.offer(startNode);
    visited[startNode] = true;

    Pair farthest = new Pair(startNode, 0);

    while (!queue.isEmpty()) {
      int current = queue.poll();
      for (int neighbor : graph.getOrDefault(current, Collections.emptyList())) {
        if (!visited[neighbor] && Math.abs(frequency[current - 1] - frequency[neighbor - 1]) <= 1) {
          visited[neighbor] = true;
          queue.offer(neighbor);
          farthest = new Pair(neighbor, farthest.dist + 1);
        }
      }
    }
    return farthest; // Return both node and its distance
  }

  public int calculateMax(int network_nodes, int[] network_from, int[] network_to, int[] frequency) {
    HashMap<Integer, List<Integer>> graph = buildGraph(network_nodes, network_from, network_to);

    // Perform the first BFS from any node, just pick 1 for simplicity
    Pair firstBfsResult = bfs(1, graph, frequency);

    // Perform the second BFS from the farthest node found from the first BFS
    Pair secondBfsResult = bfs(firstBfsResult.node, graph, frequency);

    // The distance found in the second BFS is the longest path with frequency constraints
    return secondBfsResult.dist;
  }

  public static void main(String[] args) {
    RadioWaves solution = new RadioWaves();
    int longestDistance = solution.calculateMax(5, new int[]{1, 2, 3, 1}, new int[]{2, 3, 4, 5}, new int[]{3, 2, 3, 2, 2});
    System.out.println("The longest distance is: " + longestDistance);
  }
}

