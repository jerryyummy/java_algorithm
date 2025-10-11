import java.util.Collections;
import java.util.PriorityQueue;

public class Leetcode2551 {
    public long putMarbles(int[] weights, int k) {
        int n = weights.length;
        if (k == 1) return 0L;

        // 小根堆：保留最大的 (k-1) 个
        PriorityQueue<Long> minHeapKeepMax = new PriorityQueue<>();
        // 大根堆：保留最小的 (k-1) 个
        PriorityQueue<Long> maxHeapKeepMin = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < n - 1; i++) {
            long val = (long)weights[i] + weights[i + 1];

            // 保留最大的 (k-1)
            minHeapKeepMax.offer(val);
            if (minHeapKeepMax.size() > k - 1) {
                minHeapKeepMax.poll(); // 弹出最小的，留下大的
            }

            // 保留最小的 (k-1)
            maxHeapKeepMin.offer(val);
            if (maxHeapKeepMin.size() > k - 1) {
                maxHeapKeepMin.poll(); // 弹出最大的，留下小的
            }
        }

        long sumTop = 0, sumBottom = 0;
        for (long x : minHeapKeepMax) sumTop += x;
        for (long x : maxHeapKeepMin) sumBottom += x;

        return sumTop - sumBottom;
    }
}
