public class Leetcode902 {
    public int atMostNGivenDigitSet(String[] digits, int n) {
        String s = String.valueOf(n);
        int m = s.length(), d = digits.length;
        int res = 0;

        // 1. 计算所有长度 < s.length() 的情况
        for (int i = 1; i < m; i++) {
            res += (int) Math.pow(d, i);
        }

        // 2. 计算长度 == s.length() 的情况（动态规划）
        for (int i = 0; i < m; i++) {
            boolean hasSame = false;
            for (String digit : digits) {
                if (digit.charAt(0) < s.charAt(i)) {
                    res += (int) Math.pow(d, m - i - 1); // 当前位置选择更小的数，后续随意
                } else if (digit.charAt(0) == s.charAt(i)) {
                    hasSame = true; // 需要继续检查下一位
                }
            }
            if (!hasSame) return res; // 如果当前位无法匹配 N，则无法构造更大的数
        }

        // 3. 如果 `N` 本身可以被构造，则加 1
        return res + 1;
    }
}
