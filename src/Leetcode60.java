import java.util.ArrayList;
import java.util.List;

public class Leetcode60 {
    public String getPermutation(int n, int k) {
        int[] factorials = new int[n];
        List<Integer> nums = new ArrayList() {{add(1);}};

        factorials[0] = 1;
        for (int i = 1; i < n; ++i) {
            factorials[i] = factorials[i - 1] * i;
            nums.add(i + 1);
        }

        --k;

        StringBuilder sb = new StringBuilder();
        for (int i = n - 1; i > -1; --i) {
            int idx = k / factorials[i];
            k -= idx * factorials[i];

            sb.append(nums.get(idx));
            nums.remove(idx);
        }
        return sb.toString();
    }
}
