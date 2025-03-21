import java.util.HashSet;
import java.util.Set;

public class Leetcode1980 {
    String res = "";
    boolean found = false;

    public String findDifferentBinaryString(String[] nums) {
        Set<String> set = new HashSet<>();
        for (String num : nums) {
            set.add(num);
        }

        int length = nums[0].length();
        backtrack(length, set, "");
        return res;
    }

    public void backtrack(int targetLen, Set<String> set, String cur) {
        if (found) return;

        if (cur.length() == targetLen) {
            if (!set.contains(cur)) {
                res = cur;
                found = true;
            }
            return;
        }

        backtrack(targetLen, set, cur + "0");
        backtrack(targetLen, set, cur + "1");
    }
}
