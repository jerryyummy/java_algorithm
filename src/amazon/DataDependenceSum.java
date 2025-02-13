package amazon;

import java.util.*;

public class DataDependenceSum {
    public static long getDataDependenceSum(int n) {
        int sqrt_n = (int) Math.sqrt(n);
        long sumA = 0;

        // 计算 sumA
        for (int div = 1; div <= sqrt_n; div++) {
            sumA += n / div;
        }

        int max_q = n / sqrt_n;
        long sumB = 0;

        // 计算 sumB
        for (int x = 1; x <= max_q; x++) {
            if (n / x > sqrt_n) {
                sumB += x;
            }
        }

        return sumA + sumB;
    }

    public static void main(String[] args) {
        int n = 1; // 示例测试
        System.out.println(getDataDependenceSum(n)); // 输出计算结果
    }
}

