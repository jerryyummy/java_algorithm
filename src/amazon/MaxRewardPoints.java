package amazon;

import java.util.Collections;
import java.util.List;

public class MaxRewardPoints {
    public static long findMaxPoints(List<Integer> points) {
        // Write your code here
        Collections.sort(points);
        int n = points.size();
        long ans = 0;

        // Traverse array from largest to smallest
        for (int i = 0; i < n; i++) {
            int currentValue = points.get(n - 1 - i); // Access from end since Java Arrays.sort is ascending
            if (currentValue - i > 0) {
                ans += currentValue - i;
            } else {
                break;
            }
        }
        return ans;
    }

}
