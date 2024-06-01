package snowflake;

import java.util.*;

public class TaskMaster {
  public static void main(String[] args) {
    int n = 3;
    List<Integer> a = Arrays.asList(1, 2, 3);
    List<Integer> b = Arrays.asList(2, 3, 1);

    TaskMaster tm = new TaskMaster();
    System.out.println(tm.task(n, a, b));
  }

  public int task(int n, List<Integer> a, List<Integer> b) {
    // 转换列表为数组便于索引
    int[] aArray = a.stream().mapToInt(Integer::intValue).toArray();
    int[] bArray = b.stream().mapToInt(Integer::intValue).toArray();

    // 使用邻接表表示图
    Map<Integer, List<Integer>> adjList = new HashMap<>();
    int[] inDegree = new int[n + 1];

    // 构建图
    for (int i = 0; i < aArray.length; i++) {
      adjList.computeIfAbsent(bArray[i], k -> new ArrayList<>()).add(aArray[i]);
      inDegree[aArray[i]]++;
    }

    // 拓扑排序队列
    Queue<Integer> queue = new LinkedList<>();
    for (int i = 1; i <= n; i++) {
      if (inDegree[i] == 0) {
        queue.offer(i);
      }
    }

    int completedTasks = 0;
    List<Integer> remainingTasks = new ArrayList<>();

    // 处理拓扑排序
    while (!queue.isEmpty()) {
      int current = queue.poll();
      completedTasks++;

      if (adjList.containsKey(current)) {
        for (int neighbor : adjList.get(current)) {
          inDegree[neighbor]--;
          if (inDegree[neighbor] == 0) {
            queue.offer(neighbor);
          }
        }
      }
    }

    // 找到剩余未完成的任务
    for (int i = 1; i <= n; i++) {
      if (inDegree[i] > 0) {
        remainingTasks.add(i);
      }
    }

    // 找到所有循环并计算可以完成的任务数量
    int cycleTasks = findCycles(adjList, remainingTasks);

    return completedTasks + cycleTasks;
  }

  private int findCycles(Map<Integer, List<Integer>> adjList, List<Integer> remainingTasks) {
    Set<Integer> visited = new HashSet<>();
    Set<Integer> stack = new HashSet<>();
    int cycleTasks = 0;

    for (int task : remainingTasks) {
      if (!visited.contains(task)) {
        if (hasCycle(task, adjList, visited, stack)) {
          cycleTasks++;
        }
      }
    }

    return cycleTasks;
  }

  private boolean hasCycle(int task, Map<Integer, List<Integer>> adjList, Set<Integer> visited, Set<Integer> stack) {
    if (stack.contains(task)) {
      return true;
    }
    if (visited.contains(task)) {
      return false;
    }

    visited.add(task);
    stack.add(task);

    if (adjList.containsKey(task)) {
      for (int neighbor : adjList.get(task)) {
        if (hasCycle(neighbor, adjList, visited, stack)) {
          return true;
        }
      }
    }

    stack.remove(task);
    return false;
  }
}

