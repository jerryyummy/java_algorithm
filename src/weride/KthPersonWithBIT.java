package weride;

import java.util.*;

public class KthPersonWithBIT {
    // Fenwick Tree (1-indexed)
    static class BIT {
        int n;
        int[] bit;
        BIT(int n) { this.n = n; bit = new int[n + 1]; }
        void add(int idx, int delta) {
            for (; idx <= n; idx += idx & -idx) bit[idx] += delta;
        }
        int sum(int idx) {
            int s = 0;
            for (; idx > 0; idx -= idx & -idx) s += bit[idx];
            return s;
        }
        // 返回最小 pos 使得 sum(pos) >= k；若总和 < k 返回 -1
        int kth(int k) {
            int total = sum(n);
            if (total < k) return -1;
            int pos = 0;
            int maxPow = Integer.highestOneBit(n);
            for (int step = maxPow; step != 0; step >>= 1) {
                int nxt = pos + step;
                if (nxt <= n && bit[nxt] < k) {
                    k -= bit[nxt];
                    pos = nxt;
                }
            }
            return pos + 1; // 1-indexed
        }
    }

    // p: patience array (1-indexed or 0-indexed均可，这里用0-indexed传入)
    // k: bus capacity
    // q: queries arrival times
    public static int[] kthPerson(int[] p, int k, int[] q) {
        int n = p.length, m = q.length;

        // people sorted by patience desc: (p_i, idx)
        Integer[] orderP = new Integer[n];
        for (int i = 0; i < n; i++) orderP[i] = i;
        Arrays.sort(orderP, (i, j) -> Integer.compare(p[j], p[i]));

        // queries sorted by time desc: (q_t, id)
        Integer[] orderQ = new Integer[m];
        for (int i = 0; i < m; i++) orderQ[i] = i;
        Arrays.sort(orderQ, (i, j) -> Integer.compare(q[j], q[i]));

        BIT bit = new BIT(n);
        int ptr = 0; // pointer in orderP
        int[] ans = new int[m];

        for (int qi : orderQ) {
            int time = q[qi];
            // 激活所有 p_i >= time 的人
            while (ptr < n && p[orderP[ptr]] >= time) {
                int idx = orderP[ptr] + 1; // BIT用1-index
                bit.add(idx, 1);
                ptr++;
            }
            // 查第k个
            int pos = bit.kth(k);
            ans[qi] = (pos == -1) ? 0 : pos; // pos就是原始下标(1-indexed)
        }
        return ans;
    }

    // 简单自测
    public static void main(String[] args) {
        int k = 3;
        int[] p = {2,5,3};     // 耐心
        int[] q = {1,5};        // 到站时间
        System.out.println(Arrays.toString(kthPerson(p, k, q)));
        // 解释：t=1 -> [1,2,3,4] 仍在，选第2个 => 2
        //      t=3 -> [3,4] 仍在，选第2个 => 4
        //      t=4 -> [4]   仅1人，<k => 0
        // 输出应为 [2, 4, 0]
    }
}
