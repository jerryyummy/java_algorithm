package baidu;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class MaxDataMatchRate {
    static final int INF = 0x3f3f3f3f;

    public static void solve() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();

        for (int i = 0; i < n; ++i) {
            a.add(scanner.nextInt());
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            b.add(scanner.nextInt());
            if (a.get(i).equals(b.get(i))) {
                ans++;
            }
        }

        int[] pre = new int[n];
        for (int i = 0; i < n; ++i) {
            int x = a.get(i).equals(b.get(i)) ? 1 : 0;
            int y = 0, z = 0, t = 0;
            if (i + 1 < n) {
                y = a.get(i).equals(a.get(i + 1)) ? 1 : 0;
                z = b.get(i).equals(b.get(i + 1)) ? 1 : 0;
                t = b.get(i + 1).equals(a.get(i + 1)) ? 1 : 0;
            }
            pre[i] = Math.max(Math.max(Math.max(x, y), z), t);
            if (i > 0) {
                pre[i] += pre[i - 1];
            }
        }

        int[] suf = new int[n];
        for (int i = n - 1; i >= 0; --i) {
            int x = a.get(i).equals(b.get(i)) ? 1 : 0;
            int y = 0, z = 0, t = 0;
            if (i + 1 < n) {
                y = a.get(i).equals(a.get(i + 1)) ? 1 : 0;
                z = b.get(i).equals(b.get(i + 1)) ? 1 : 0;
                t = b.get(i + 1).equals(a.get(i + 1)) ? 1 : 0;
            }
            suf[i] = Math.max(Math.max(Math.max(x, y), z), t);
            if (i < n - 1) {
                suf[i] += suf[i + 1];
            }
        }

        ans = Math.max(ans, pre[n - 1]);
        ans = Math.max(ans, suf[0]);
        if (n >= 2) {
            ans = Math.max(ans, Math.max(pre[n - 2], suf[1]));
        }

        for (int i = 1; i < n - 1; ++i) {
            int cur = (i > 1) ? pre[i - 2] : 0;
            cur += suf[i + 1];
            int maxVal = 0;
            if (i - 1 >= 0 && i + 1 < n) {
                maxVal = Math.max(
                        a.get(i - 1).equals(b.get(i - 1)) ? 1 : 0,
                        Math.max(
                                a.get(i - 1).equals(a.get(i + 1)) ? 1 : 0,
                                Math.max(
                                        b.get(i - 1).equals(b.get(i + 1)) ? 1 : 0,
                                        a.get(i + 1).equals(b.get(i + 1)) ? 1 : 0
                                )
                        )
                );
            }
            cur += maxVal;
            ans = Math.max(ans, cur);
        }

        System.out.println(ans);
    }

    public static void main(String[] args) {
        solve();
    }
}
