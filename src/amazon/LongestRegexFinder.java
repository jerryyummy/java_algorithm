package amazon;

import java.util.*;

public class LongestRegexFinder {
    public static String findLongestRegex(String x, String y, String z) {
        int n = x.length();
        if (y.length() != n || z.length() != n) {
            return "-1";
        }

        List<Integer> invalid = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (z.charAt(i) != x.charAt(i) && z.charAt(i) != y.charAt(i)) {
                invalid.add(i);
            }
        }

        if (invalid.isEmpty()) {
            return "-1";
        }

        int exIndex = invalid.get(invalid.size() - 1); // 最后一个无效索引
        Set<Character> allUpper = new HashSet<>();
        for (char c = 'A'; c <= 'Z'; c++) {
            allUpper.add(c);
        }

        StringBuilder regexBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            Set<Character> curSet = new HashSet<>(allUpper);
            curSet.add(x.charAt(i));
            curSet.add(y.charAt(i));

            if (i == exIndex) {
                curSet.remove(z.charAt(i)); // 移除无效字符
            }

            if (curSet.isEmpty()) {
                return "-1";
            }

            StringBuilder regexPart = new StringBuilder("[");
            curSet.stream().sorted().forEach(regexPart::append);
            regexPart.append("]");
            regexBuilder.append(regexPart);
        }

        return regexBuilder.toString();
    }

    public static void main(String[] args) {
        // 测试用例
        String x = "ABC";
        String y = "BCD";
        String z = "ACE";

        System.out.println(findLongestRegex(x, y, z)); // 预期输出
    }
}

