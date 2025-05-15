package snowflake;

import java.util.ArrayDeque;
import java.util.Deque;

public class EncryptingStrings {
    public static String getEncryptedString(String originalString){
        int n = originalString.length();
        Deque<Character> temp = new ArrayDeque<>();
        StringBuilder result = new StringBuilder();

        // 后缀最小字符数组
        char[] minSuffix = new char[n];
        minSuffix[n - 1] = originalString.charAt(n - 1);
        for (int i = n - 2; i >= 0; i--) {
            minSuffix[i] = (char) Math.min(originalString.charAt(i), minSuffix[i + 1]);
        }

        int i = 0;
        while (i < n || !temp.isEmpty()) {
            if (!temp.isEmpty()) {
                // 如果当前 originalString 剩下的最小字符 >= temp 的末尾字符 → 弹出
                if (i == n || temp.peekLast() <= minSuffix[i]) {
                    result.append(temp.removeLast());
                    continue;
                }
            }

            if (i < n) {
                temp.addLast(originalString.charAt(i));
                i++;
            }
        }

        return result.toString();
    }
}
