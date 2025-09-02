package axon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MinOp {
    static long needOps(long a, long M, long x) {
        if (a <= M) return 0L;
        long diff = a - M;
        return (diff + x - 1) / x; // ceil((a-M)/x)
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long k = Long.parseLong(st.nextToken());
        long x = Long.parseLong(st.nextToken());

        long[] a = new long[n];
        st = new StringTokenizer(br.readLine());
        long mn = Long.MAX_VALUE, mx = Long.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            a[i] = Long.parseLong(st.nextToken());
            mn = Math.min(mn, a[i]);
            mx = Math.max(mx, a[i]);
        }

        // 二分最小可行的最大值 M
        long low = mn - k * x - 5; // 安全下界
        long high = mx;            // 上界
        while (low < high) {
            long mid = low + ((high - low) >> 1);
            // 计算把所有数降到 <= mid 需要的总操作数
            long need = 0;
            for (long v : a) {
                need += needOps(v, mid, x);
                if (need > k) break; // 剪枝
            }
            if (need <= k) high = mid;   // 可行，尝试更小的最大值
            else low = mid + 1;          // 不可行，提高 M
        }
        System.out.println(high);
    }
}
