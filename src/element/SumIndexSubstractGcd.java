package element;

import java.util.Scanner;

public class SumIndexSubstractGcd {
    private static final int MOD = 1_000_000_007; // 10^9 + 7
    private static final int MAX_N = 10_000_000; // 假设n的最大值为1e7
    private static int[] phi; // 欧拉函数数组
    private static long[] sum; // 前缀和数组 S(b) = sum_{i=2}^b i * phi(i)

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt(); // 测试用例数

        // 预处理欧拉函数和前缀和
        precomputeEulerTotientAndSum(MAX_N);

        while (T-- > 0) {
            int n = scanner.nextInt();
            System.out.println(calculateSum(n));
        }

        scanner.close();
    }

    // 使用线性筛法预处理欧拉函数和前缀和
    private static void precomputeEulerTotientAndSum(int n) {
        phi = new int[n + 1];
        sum = new long[n + 1];
        int[] primes = new int[n + 1];
        int primeCount = 0;

        // 初始化欧拉函数
        for (int i = 2; i <= n; i++) {
            if (phi[i] == 0) { // i是质数
                phi[i] = i - 1;
                primes[primeCount++] = i;
            }
            for (int j = 0; j < primeCount && primes[j] * i <= n; j++) {
                int p = primes[j];
                int multiple = p * i;
                if (i % p == 0) {
                    phi[multiple] = phi[i] * p;
                    break;
                } else {
                    phi[multiple] = phi[i] * (p - 1);
                }
            }
        }

        // 计算前缀和 S(b) = sum_{i=2}^b i * phi(i)
        for (int i = 2; i <= n; i++) {
            sum[i] = sum[i - 1] + (long) i * phi[i];
            if (sum[i] >= MOD) sum[i] -= MOD; // 减少模运算次数
        }
    }

    // 计算 (i+j)/gcd(i,j) 的和，其中 1 ≤ i < j ≤ n
    private static long calculateSum(int n) {
        long total = 0;

        // 数论分块优化
        int lastK = 0;
        for (int k = 1; k <= n; k = lastK + 1) {
            int limit = n / k; // 最大的a或b值
            if (limit < 2) break; // 至少需要a < b

            // 找到最大的k'，使得 n / k' == limit
            lastK = n / limit;

            // 贡献为 (3/2) * sum_{b=2}^{limit} b * phi(b)
            long contribution = sum[limit];
            contribution = (contribution * 3) % MOD;
            contribution = (contribution * 500_000_004) % MOD; // 乘以 1/2 的模逆元

            // 将这个gcd值的贡献乘以块的大小 (lastK - k + 1)
            long blockSize = lastK - k + 1;
            contribution = (contribution * blockSize) % MOD;

            // 将这个gcd值的贡献加到总和中
            total += contribution;
            if (total >= MOD) total -= MOD; // 减少模运算次数
        }

        return total;
    }
}
