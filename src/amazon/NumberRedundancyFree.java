package amazon;

import java.util.HashSet;
import java.util.Set;

public class NumberRedundancyFree {
    public int soln(String password) {
        int count = 1;
        Set<Character> seen = new HashSet<>();

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (seen.contains(c)) {
                seen.clear();
                count++;
            }
            seen.add(c);
        }

        return count;
    }

    public static void main(String[] args) {
        NumberRedundancyFree numberRedundancyFree = new NumberRedundancyFree();
        System.out.println(numberRedundancyFree.soln("aabcdea")); // 示例调用
    }
}
