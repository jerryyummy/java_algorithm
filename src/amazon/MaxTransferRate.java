package amazon;

import java.util.Arrays;

public class MaxTransferRate {
    public static long maxTransferRate(int[] throughput, int pipelineCount) {
        int n = throughput.length;
        Arrays.sort(throughput);

        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + throughput[i];
        }

        int low = throughput[0] * 2, high = throughput[n - 1] * 2 + 1;

        while (low < high) {
            int mid = (low + high) / 2;
            if (countPairs(throughput, mid) >= pipelineCount) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        int t = low - 1;
        long totalPairs = 0, totalSum = 0;
        int j = n - 1;

        for (int i = 0; i < n; i++) {
            while (j >= 0 && throughput[i] + throughput[j] >= t) {
                j--;
            }
            int count = n - j - 1;
            totalPairs += count;
            totalSum += (long) throughput[i] * count + (prefix[n] - prefix[j + 1]);
        }

        long excess = totalPairs - pipelineCount;
        totalSum -= excess * t;

        return totalSum;
    }

    private static int countPairs(int[] throughput, int t) {
        int n = throughput.length;
        int count = 0, j = n - 1;

        for (int i = 0; i < n; i++) {
            while (j >= 0 && throughput[i] + throughput[j] >= t) {
                j--;
            }
            count += n - j - 1;
        }

        return count;
    }

    public static void main(String[] args) {
        int[] throughput = {4,2,5};
        int pipelineCount = 4;
        System.out.println(maxTransferRate(throughput, pipelineCount)); // 测试代码
    }
}
