import java.util.Comparator;
import java.util.PriorityQueue;

public class Leetcode646 {
  public int findLongestChain(int[][] pairs) {
    PriorityQueue<int[]> queue = new PriorityQueue<>((a,b)->a[1]-b[1]);

    for (int[] pair : pairs) {
      queue.offer(pair);
    }

    int max = Integer.MIN_VALUE;
    int res = 0;
    while (!queue.isEmpty()) {
      int[] pair = queue.poll();
      if(pair[0]<=max) continue;
      else{
        max = pair[1];
        res++;
      }
    }

    return res;
  }
}
