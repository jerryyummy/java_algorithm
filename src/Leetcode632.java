import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Leetcode632 {
    public int[] smallestRange(List<List<Integer>> nums) {
        int k = nums.size();
        // 最小堆：元素为 [值, 列表id, 列内索引]
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        int curMax = Integer.MIN_VALUE;

        // 初始化：每个列表第一个元素入堆，同时维护当前最大值
        for (int i = 0; i < k; i++) {
            int v = nums.get(i).get(0);
            pq.offer(new int[]{v, i, 0});
            curMax = Math.max(curMax, v);
        }

        // 用一个很宽的哨兵区间；也可以在第一次弹出后再初始化
        int bestL = -1_000_000_000, bestR = 1_000_000_000;

        while (true) {
            int[] curr = pq.poll();
            int minVal = curr[0];
            int listId = curr[1];
            int idxInList = curr[2];

            // 比较时用 long，避免溢出；同长取左端点更小
            long curLen = (long) curMax - (long) minVal;
            long bestLen = (long) bestR - (long) bestL;
            if (curLen < bestLen || (curLen == bestLen && minVal < bestL)) {
                bestL = minVal;
                bestR = curMax;
            }

            // 推进该列表指针；若该列表已耗尽就停止（无法再覆盖所有列表）
            if (idxInList + 1 == nums.get(listId).size()) {
                break;
            }
            int nextVal = nums.get(listId).get(idxInList + 1);
            curMax = Math.max(curMax, nextVal);
            pq.offer(new int[]{nextVal, listId, idxInList + 1});
        }

        return new int[]{bestL, bestR};
    }
}
