package alibaba;

import java.util.Arrays;

public class MinRangeAfterOperations {
    public static int minRangeAfterOperations(int[] arr, int n, int m) {
        Arrays.sort(arr); // 先对数组进行排序
        int minVal = arr[0]; // 数组最小值
        int maxVal = arr[arr.length - 1]; // 数组最大值
        int minRange = maxVal - minVal; // 初始极差

        // 遍历 i，表示前 i 个数增加 m，后 n-i 个数减少 m
        for (int i = 0; i <= n; i++) {
            int newMin = Math.min(arr[0] + m, arr[i] - m);
            int newMax = Math.max(arr[arr.length - 1] - m,
                    (i + n - 1 < arr.length) ? arr[i + n - 1] + m : arr[arr.length - 1]);

            minRange = Math.min(minRange, newMax - newMin);
        }

        return minRange;
    }

    public static void main(String[] args) {
        int[] arr = {1, 5, 9, 12};
        int n = 2;
        int m = 3;
        System.out.println(minRangeAfterOperations(arr, n, m)); // 输出可能的最小极差
    }
}
