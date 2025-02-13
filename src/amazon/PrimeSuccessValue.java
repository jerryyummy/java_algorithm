package amazon;

import java.util.Arrays;

public class PrimeSuccessValue {
    public int[] getSuccessValue(int[] num_viewers, int[] queries) {
        Arrays.sort(num_viewers);
        // custom helper or do a built-in approach
        reverse(num_viewers);
        int[] sum = new int[num_viewers.length];
        sum[0] = num_viewers[0];
        for (int i = 1; i < num_viewers.length; i++) {
            sum[i] = sum[i - 1] + num_viewers[i];
        }

        int[] results = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            results[i] = sum[queries[i] - 1];
        }
        return results;
    }

    private static void reverse(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++; right--;
        }
    }
}
