package klook;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
给定字符串长度为n的全为‘a'的字符串，可以操作k次，每次可以修改一个字符为另外一个小写字符，问k次一共可以得到多少个不同的字符串，取模 1_000_000_007L
 */
public class StringTransform {
    static final long MOD = 1_000_000_007L;
    static long[] fact, invFact;

    static long modPow(long a, long e) {
        long r = 1;
        a %= MOD;
        while (e > 0) {
            if ((e & 1) == 1) r = (r * a) % MOD;
            a = (a * a) % MOD;
            e >>= 1;
        }
        return r;
    }

    static long C(int n, int k) {
        if (k < 0 || k > n) return 0;
        return (((fact[n] * invFact[k]) % MOD) * invFact[n - k]) % MOD;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] sp = br.readLine().trim().split("\\s+");
        int n = Integer.parseInt(sp[0]);
        long k = Long.parseLong(sp[1]);

        // 预处理阶乘与逆元
        fact = new long[n + 1];
        invFact = new long[n + 1];
        fact[0] = 1;
        for (int i = 1; i <= n; i++) fact[i] = (fact[i - 1] * i) % MOD;
        invFact[n] = modPow(fact[n], MOD - 2);
        for (int i = n - 1; i >= 0; i--) invFact[i] = (invFact[i + 1] * (i + 1)) % MOD;

        int up = (int) Math.min(n, k);
        long ans = 0;

        long pow25 = 1; // 25^0
        for (int d = 0; d <= up; d++) {
            long term = (C(n, d) * pow25) % MOD;
            ans += term;
            if (ans >= MOD) ans -= MOD;
            pow25 = (pow25 * 25) % MOD; // 下一次是 25^(d+1)
        }

        if (k == 1) { // 只能得到海明距离为1的串，d=0（全'a'）不可达
            ans = (ans - 1) % MOD;
            if (ans < 0) ans += MOD;
        }

        System.out.println(ans);
    }
}
