package amazon;

import java.util.Arrays;

public class DistributedCenter {
    public int getMinTotalDistance(int[] dist_centers) {
        // write your code here
        // Sort the distribution centers
        Arrays.sort(dist_centers);
        int n = dist_centers.length;

        // Initialize the minimum distance sum to a large number
        int minDistanceSum = Integer.MAX_VALUE;

        // Try different partition points
        for (int i = 1; i < n; i++) {
            int median1 = median(dist_centers, 0, i - 1);
            int median2 = median(dist_centers, i, n - 1);

            int currentDistanceSum = 0;

            for (int center : dist_centers) {
                int distanceToClosestWarehouse = Math.min(Math.abs(center - median1), Math.abs(center - median2));
                currentDistanceSum += distanceToClosestWarehouse;
            }

            minDistanceSum = Math.min(minDistanceSum, currentDistanceSum);
        }

        return minDistanceSum;
    }

    private int median(int[] arr, int start, int end) {
        int length = end - start + 1;
        if (length % 2 == 0) {
            return (arr[start + length / 2 - 1] + arr[start + length / 2]) / 2;
        } else {
            return arr[start + length / 2];
        }
    }
}
