import java.util.Map;
import java.util.TreeMap;


/*
You are given an integer array arr. From some starting index, you can make a series of jumps. The (1st, 3rd, 5th, ...) jumps in the series are called
odd-numbered jumps, and the (2nd, 4th, 6th, ...) jumps in the series are called even-numbered jumps. Note that the jumps are numbered, not the indices.
You may jump forward from index i to index j (with i < j) in the following way:
During odd-numbered jumps (i.e., jumps 1, 3, 5, ...), you jump to the index j such that arr[i] <= arr[j] and arr[j] is the smallest possible value.
If there are multiple such indices j, you can only jump to the smallest such index j.
During even-numbered jumps (i.e., jumps 2, 4, 6, ...), you jump to the index j such that arr[i] >= arr[j] and arr[j] is the largest possible value.
If there are multiple such indices j, you can only jump to the smallest such index j.
It may be the case that for some index i, there are no legal jumps.
A starting index is good if, starting from that index, you can reach the end of the array (index arr.length - 1) by
jumping some number of times (possibly 0 or more than once).
Return the number of good starting indices.
 */

public class OddEvenJump {
    public int oddEvenJumps(int[] A) {
        int n  = A.length, res = 1;
        boolean[] higher = new boolean[n],//从奇数跳可以到达末尾
                lower = new boolean[n];//从偶数跳可以到达末尾
        higher[n - 1] = lower[n - 1] = true;//初始化最后一个位置，无论怎么都已经到达末尾
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(A[n - 1], n - 1);
        for (int i = n - 2; i >= 0; --i) {
            Map.Entry<Integer, Integer> hi = map.ceilingEntry(A[i]);//找到符合条件最小的树
            Map.Entry<Integer, Integer> lo = map.floorEntry(A[i]);//找到符合条件最大的数
            if (hi != null) higher[i] = lower[hi.getValue()];
            if (lo != null) lower[i] = higher[lo.getValue()];
            if (higher[i]) res++;
            map.put(A[i], i);
        }
        return res;
    }
}
