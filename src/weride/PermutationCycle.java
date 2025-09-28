package weride;

import java.util.*;

/**
 * 返回最少需要的操作次数（对 1e9+7 取模）
 * p[i] 取值 1..n，表示位置 i+1 的元素被搬到 p[i] 位置
 */
public class PermutationCycle {
    static final long MOD = 1_000_000_007L;

    public static int countOperations(int[] p) {
        int n = p.length;
        boolean[] vis = new boolean[n];

        // 预生成素数用于分解环长
        List<Integer> primes = sieve(n);

        // 记录 lcm 的质因数最大幂次
        Map<Integer, Integer> maxExp = new HashMap<>();

        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                int len = 0;
                int cur = i;
                // 注意：p 是 1-based 映射到 0-based 索引
                while (!vis[cur]) {
                    vis[cur] = true;
                    cur = p[cur] - 1;
                    len++;
                }
                if (len > 0) {
                    factorizeInto(len, primes, maxExp);
                }
            }
        }

        long ans = 1L;
        for (Map.Entry<Integer, Integer> e : maxExp.entrySet()) {
            ans = (ans * modPow(e.getKey(), e.getValue(), MOD)) % MOD;
        }
        return (int) ans;
    }

    // 用筛法生成 <= n 的素数
    private static List<Integer> sieve(int n) {
        boolean[] isComp = new boolean[n + 1];
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (!isComp[i]) {
                primes.add(i);
                if ((long) i * i <= n) {
                    for (int j = i * i; j <= n; j += i) isComp[j] = true;
                }
            }
        }
        return primes;
    }

    // 把 x 的质因数分解累加到 maxExp 中（取最大幂次）
    private static void factorizeInto(int x, List<Integer> primes, Map<Integer, Integer> maxExp) {
        int temp = x;
        for (int p : primes) {
            if ((long) p * p > temp) break;
            if (temp % p == 0) {
                int cnt = 0;
                while (temp % p == 0) {
                    temp /= p;
                    cnt++;
                }
                maxExp.put(p, Math.max(maxExp.getOrDefault(p, 0), cnt));
            }
        }
        if (temp > 1) { // 剩下的是一个质数
            maxExp.put(temp, Math.max(maxExp.getOrDefault(temp, 0), 1));
        }
    }

    private static long modPow(long a, int e, long mod) {
        long res = 1;
        long base = a % mod;
        int exp = e;
        while (exp > 0) {
            if ((exp & 1) == 1) res = (res * base) % mod;
            base = (base * base) % mod;
            exp >>= 1;
        }
        return res;
    }

    // ---- 简单测试 ----
    public static void main(String[] args) {
        System.out.println(countOperations(new int[]{2, 3, 1}));          // 3
        System.out.println(countOperations(new int[]{2, 5, 4, 3, 1}));    // 6
        System.out.println(countOperations(new int[]{1, 2, 3}));          // 1
    }
}