import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Leetcode3458 {
    public boolean maxSubstringLength(String s, int k) {
        int n = s.length();
        if (k == 0) return true;

        int[] left = new int[26];
        int[] right = new int[26];
        Arrays.fill(left, n);
        Arrays.fill(right, -1);

        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            left[c] = Math.min(left[c], i);
            right[c] = Math.max(right[c], i);
        }

        List<int[]> intervals = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (i != left[s.charAt(i) - 'a']) continue;
            int end = right[s.charAt(i) - 'a'];
            int j = i;
            boolean valid = true;

            while (j <= end) {
                if (left[s.charAt(j) - 'a'] < i) {
                    valid = false;
                    break;
                }
                end = Math.max(end, right[s.charAt(j) - 'a']);
                j++;
            }

            if (valid && !(i == 0 && end == n - 1)) {
                intervals.add(new int[]{i, end});
            }
        }

        intervals.sort(Comparator.comparingInt(a -> a[1]));

        int count = 0, prev = -1;
        for (int[] inter : intervals) {
            if (inter[0] > prev) {
                count++;
                prev = inter[1];
            }
        }

        return count >= k;
    }
}
