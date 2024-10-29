import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Leetcode1481 {
  public int findLeastNumOfUniqueInts(int[] arr, int k) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int num : arr) {
      map.put(num, map.getOrDefault(num, 0) + 1);
    }
    PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[1]-b[1]);
    for (int num : map.keySet()) {
      pq.offer(new int[]{num, map.get(num)});
    }
    while (k>0){
      int[] temp = pq.poll();
      if(temp[1]>k){
        k=0;
        pq.offer(new int[]{temp[0], temp[1]-k});
      }else{
        k-=temp[1];
      }
    }
    return pq.size();
  }
}
