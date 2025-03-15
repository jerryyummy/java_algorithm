import java.util.Arrays;

public class Leetcode3474 {
    public String generateString(String S, String t) {
        char[] s = S.toCharArray();
        int n = s.length;
        int m = t.length();
        char[] ans = new char[n + m - 1];
        Arrays.fill(ans, '?'); // '?' 表示待定位置

        // 处理 T
        for (int i = 0; i < n; i++) {
            if (s[i] != 'T') {
                continue;
            }
            // 子串必须等于 t
            for (int j = 0; j < m; j++) {
                char v = ans[i + j];
                if (v != '?' && v != t.charAt(j)) {
                    return "";
                }
                ans[i + j] = t.charAt(j);
            }
        }

        char[] oldAns = ans.clone();
        for (int i = 0; i < ans.length; i++) {
            if (ans[i] == '?') {
                ans[i] = 'a'; // 待定位置的初始值为 'a'
            }
        }

        // 处理 F
        for (int i = 0; i < n; i++) {
            if (s[i] != 'F') {
                continue;
            }
            // 子串必须不等于 t
            if (!new String(ans, i, m).equals(t)) {
                continue;
            }
            // 找最后一个待定位置
            boolean ok = false;
            for (int j = i + m - 1; j >= i; j--) {
                if (oldAns[j] == '?') { // 之前填 'a'，现在改成 'b'
                    ans[j] = 'b';
                    ok = true;
                    break;
                }
            }
            if (!ok) {
                return "";
            }
        }

        return new String(ans);
    }
}
