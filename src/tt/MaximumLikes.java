package tt;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaximumLikes {
    public static int maximumLikes(List<Integer> predictions) {
        final long MOD = 1000000007L;

        // Handle empty or null input
        if (predictions == null || predictions.isEmpty()) {
            return 0;
        }

        // Count frequency of each number
        Map<Integer, Long> frequencyMap = new HashMap<>();
        for (int val : predictions) {
            frequencyMap.merge(val, 1L, Long::sum);
        }

        if (frequencyMap.isEmpty()) {
            return 0;
        }

        // Find the maximum value
        int maxValue = Collections.max(frequencyMap.keySet());

        // Dynamic programming array
        long[] dpTable = new long[maxValue + 1];
        dpTable[1] = frequencyMap.getOrDefault(1, 0L);

        // Fill dp table
        for (int i = 2; i <= maxValue; i++) {
            long currentCount = frequencyMap.getOrDefault(i, 0L);
            dpTable[i] = Math.max(dpTable[i - 1], dpTable[i - 2] + i * currentCount);
        }

        return (int) (dpTable[maxValue] % MOD);
    }
}
