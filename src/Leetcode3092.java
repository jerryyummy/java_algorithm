import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Leetcode3092 {
  public long[] mostFrequentIDs(int[] nums, int[] freq) {
    Map<Long, Long> hm = new HashMap<>();
    PriorityQueue<long[]> pq = new PriorityQueue<>((long[] a, long[] b) -> {
      return Long.compare(b[1], a[1]);
    });

    int n = nums.length;
    long[] result = new long[n];
    for(int i=0; i<n; i++) {
      long num = (long)nums[i];
      long count = (long)freq[i];

      hm.put(num, hm.getOrDefault(num, 0L) + count);
      pq.add(new long[]{num, hm.get(num)});

      while(pq.peek()[1] != hm.get(pq.peek()[0]))
        pq.poll();

      result[i] = pq.peek()[1];
    }

    return result;
  }
}
