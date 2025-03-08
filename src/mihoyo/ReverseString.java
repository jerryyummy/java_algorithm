package mihoyo;

import java.util.*;

public class ReverseString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        scanner.nextLine(); // 读取换行符
        String s = scanner.nextLine();

        // 计算 prefix 和 suffix
        int[] prefix = new int[n];
        int[] suffix = new int[n];
        Arrays.fill(prefix, -1);
        Arrays.fill(suffix, -1);

        // 使用中心扩展法计算所有长度至少为 2 的回文子串
        computePalindromeRanges(s, n, prefix, suffix);

        // 查找符合条件的 (a, b, c, d)
        for (int i = 0; i < n / 2; i++) {
            if (prefix[i] != -1 && suffix[n - i - 1] != -1 &&
                    prefix[i] < suffix[n - i - 1]) {
                System.out.println("YES");
                System.out.println((i + 1) + " " + (prefix[i] + 1) + " " +
                        (suffix[n - i - 1] + 1) + " " + (n - i));
                return;
            }
        }

        System.out.println("NO");
    }

    // 使用中心扩展法找到每个位置可以形成的最小回文区间（长度至少为 2）
    private static void computePalindromeRanges(String s, int n, int[] prefix, int[] suffix) {
        for (int center = 0; center < n; center++) {
            // 奇数长度回文（长度至少 2）
            expandAndMark(s, n, center, center + 1, prefix, suffix);
            // 偶数长度回文（长度至少 2）
            if (center + 2 < n) {
                expandAndMark(s, n, center, center + 2, prefix, suffix);
            }
        }
    }

    // 以 left 和 right 作为回文中心向两侧扩展
    private static void expandAndMark(String s, int n, int left, int right, int[] prefix, int[] suffix) {
        while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
            if (prefix[left] == -1 || right < prefix[left]) {
                prefix[left] = right;
            }
            if (suffix[right] == -1 || left > suffix[right]) {
                suffix[right] = left;
            }
            left--;
            right++;
        }
    }
}
