package klook;

import java.io.*;
import java.util.*;

/**
 * 选择 k 个不同的连续子数组，使得每个子数组和能被 t 整除，
 * 且总和最大。n, k, t <= 1e5，ai <= 1e5。
 */
public class ValidInterval {

    // 快速读入
    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;

        FastScanner(InputStream is) {
            this.in = is;
        }

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        long nextLong() throws IOException {
            int c;
            do {
                c = read();
            } while (c <= ' '); // skip space
            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }
            long x = 0;
            while (c > ' ') {
                x = x * 10 + c - '0';
                c = read();
            }
            return x * sign;
        }

        int nextInt() throws IOException {
            return (int) nextLong();
        }
    }

    // 堆中元素：对应某个余数类 cid、固定右端 j、当前左端 i
    static class Node {
        int cid, i, j;
        long val;
        Node(int cid, int i, int j, long val) {
            this.cid = cid;
            this.i = i;
            this.j = j;
            this.val = val;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        int k = fs.nextInt();
        int t = fs.nextInt();

        // 读数组并构造每个余数类的前缀和序列
        @SuppressWarnings("unchecked")
        ArrayList<Long>[] buckets = new ArrayList[t];
        for (int i = 0; i < t; i++) buckets[i] = new ArrayList<>();

        long pref = 0;
        buckets[0].add(0L); // P[0]=0 在余数 0 类
        for (int i = 0; i < n; i++) {
            long a = fs.nextLong();
            pref += a;
            int r = (int) (pref % t);
            buckets[r].add(pref);
        }

        // 全局最大堆（按子数组和降序）
        PriorityQueue<Node> pq = new PriorityQueue<>((x, y) -> Long.compare(y.val,
                x.val));

        // 初始化：对每个余数类的每个 j，先放 (i=0, j) 这个最大的差值
        for (int cid = 0; cid < t; cid++) {
            ArrayList<Long> S = buckets[cid];
            int m = S.size();
            for (int j = 1; j < m; j++) {
                long val = S.get(j) - S.get(0);
                pq.offer(new Node(cid, 0, j, val));
            }
        }

        long ans = 0;
        for (int cnt = 0; cnt < k; cnt++) {
            Node cur = pq.poll();            // 题目保证至少有 k 个合法子数组
            ans += cur.val;

            // 推进该固定 j 的下一候选 (i+1, j)
            int ni = cur.i + 1;
            if (ni < cur.j) {
                ArrayList<Long> S = buckets[cur.cid];
                long nval = S.get(cur.j) - S.get(ni);
                pq.offer(new Node(cur.cid, ni, cur.j, nval));
            }
        }

        System.out.println(ans);
    }
}