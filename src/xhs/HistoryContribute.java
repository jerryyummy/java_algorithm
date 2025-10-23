package xhs;

import java.util.Scanner;

/*
题目描述：小红书算法团队为了优化用户等级历史贡献统计，记录了一个长度为 n 的数组{a1,a2,...,an}。
该数组中的元素均为正整数，并满足非严格递增关系：a1≤a2≤···≤an。但由于网络波动，小红书后台日志中部分位置的值丢失，用 ai= 0 表示丢失数据；
题目保证 a1,an 不为 0。请统计符合上述要求的原始数组的可能方案数。由于方案数可能非常大，请将结果对(109+7)取模后输出。

输入描述
在一行上输入一个整数n(1≤n≤2*105)，表示数组长度。
在第二行输入 n 个整数a1,a2,...,an(0≤ai≤109)，其中 ai=0 表示丢失数据，其余位置满足非严格递增关系。

输出描述
在一行上输出一个整数，表示符合条件的原始数组的方案数对(109+7)取模后的结果。
 */
public class HistoryContribute {
    static final long MOD = (long) 1e9 + 7;

    // 预处理阶乘的逆元表
    static long[] preFact(int maxVal, long mod) {
        long[] fact = new long[maxVal + 1];
        fact[0] = 1;
        for (int i = 1; i <= maxVal; i++) {
            fact[i] = (fact[i - 1] * i) % mod;
        }

        long[] invFact = new long[maxVal + 1];
        // 费马小定理求最大下标的逆元
        invFact[maxVal] = powMod(fact[maxVal], mod - 2, mod);
        for (int i = maxVal - 1; i >= 0; i--) {
            invFact[i] = (invFact[i + 1] * (i + 1)) % mod;
        }
        return invFact;
    }

    // 快速幂实现
    static long powMod(long a, long b, long mod) {
        long result = 1;
        a = a % mod;
        while (b > 0) {
            if (b % 2 == 1) {
                result = (result * a) % mod;
            }
            a = (a * a) % mod;
            b = b / 2;
        }
        return result;
    }

    static long comb(int n, int k, long mod, long[] invFactTable) {
        if (k < 0 || k > n) {
            return 0;
        }
        if (k == 0 || k == n) {
            return 1;
        }

        // 计算分子：n*(n-1)*...*(n-k+1)
        long numerator = 1;
        for (int i = 0; i < k; i++) {
            numerator = (numerator * (n - i)) % mod;
        }

        long denominatorInv = invFactTable[k];
        return (numerator * denominatorInv) % mod;
    }

    static void solve() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        scanner.close();

        long[] invFactTable = preFact(n, MOD);
        long totalWays = 1;

        int lastNonzeroIdx = 0;
        int leftVal = a[0];

        for (int i = 1; i < n; i++) {
            if (a[i] != 0) {
                int rightVal = a[i];
                int k = i - lastNonzeroIdx - 1;

                if (k > 0) {
                    int nComb = rightVal - leftVal + k;
                    int kComb = k;

                    long waysSegment = comb(nComb, kComb, MOD, invFactTable);
                    totalWays = (totalWays * waysSegment) % MOD;
                }

                lastNonzeroIdx = i;
                leftVal = a[i];
            }
        }

        System.out.println(totalWays);
    }

    public static void main(String[] args) {
        solve();
    }
}
