package meituan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class MinSwap {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int[] array = new int[n];
    for (int i = 0; i < n; i++) {
      array[i] = in.nextInt();
    }

    String s = in.next();
    char[] colors = new char[n];
    for (int i = 0; i < n; i++) {
      colors[i] = s.charAt(i);
    }

    Queue<Integer> queue = new PriorityQueue<>();
    ArrayList<Integer> list = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      if (colors[i] == 'R'){
        queue.add(array[i]);
        list.add(array[i]);
      }
    }

    for (int i = 0; i < n; i++) {
      if (colors[i] == 'R'){
        array[i] = queue.remove();
      }
    }

    for (int i = 1; i < n; i++) {
      if (array[i] - array[i - 1] != 1) {
        System.out.println(-1);
        return;
      }
    }

    System.out.println(minSwapsToSort(list));
  }

  public static int minSwapsToSort(List<Integer> list) {
    int n = list.size();
    int[] arr = list.stream().mapToInt(i->i).toArray();
    boolean[] visited = new boolean[n];
    Map<Integer, Integer> valueToIndex = new HashMap<>();

    for (int i = 0; i < n; i++) {
      valueToIndex.put(arr[i], i);
    }

    arr = java.util.Arrays.stream(arr).boxed().sorted().mapToInt(i->i).toArray();
    for (int i = 0; i < n; i++) {
      arr[i] = valueToIndex.get(arr[i]);
    }

    int swaps = 0;
    for (int i = 0; i < n; i++) {
      if (visited[i] || arr[i] == i) continue;
      int cycleSize = 0;
      int j = i;
      while (!visited[j]) {
        visited[j] = true;
        j = arr[j];
        cycleSize++;
      }
      swaps += (cycleSize - 1);
    }

    return swaps;
  }

}
