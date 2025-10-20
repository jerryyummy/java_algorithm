import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Leetcode1590 {
    public int minSubarray(int[] nums, int p) {
        int n = nums.length;
        long sum = 0L;
        for (int x : nums) sum += x;

        int need = (int)(sum % p);
        if (need == 0) return 0;  // 已经能整除，无需删除

        // Map: key = prefix 模值，value = 该模值最后一次出现的下标
        Map<Integer, Integer> last = new HashMap<>();
        last.put(0, -1);  // 空前缀模为0，对应下标-1

        int pref = 0;     // 当前前缀和的模
        int ans = n;      // 记录最短长度

        for (int i = 0; i < n; i++) {
            // 当前前缀模 (使用 floorMod 确保非负)
            pref = Math.floorMod(pref + nums[i], p);

            // 我们需要找到一个 j，使得 prefix[j] == (pref - need) mod p
            int want = Math.floorMod(pref - need, p);
            if (last.containsKey(want)) {
                ans = Math.min(ans, i - last.get(want));
            }

            // 更新当前模值的位置
            last.put(pref, i);
        }

        return ans == n ? -1 : ans;
    }
}
