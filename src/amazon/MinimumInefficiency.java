package amazon;

import java.util.*;

public class MinimumInefficiency {
    public int findMinimumInefficiency(String serverType) {
        char[] servers = serverType.toCharArray();
        int n = servers.length;
        int inefficiency = 0;

        // 1. 处理前导 '?'
        int firstNonQuestionMark = 0;
        while (firstNonQuestionMark < n && servers[firstNonQuestionMark] == '?') {
            firstNonQuestionMark++;
        }

        if (firstNonQuestionMark == n) {
            return 0; // 全是 '?'，无低效率
        }

        for (int i = 0; i < firstNonQuestionMark; i++) {
            servers[i] = servers[firstNonQuestionMark];
        }

        // 2. 处理其余 '?'
        for (int i = firstNonQuestionMark; i < n; i++) {
            if (servers[i] == '?') {
                servers[i] = servers[i - 1];
            }
            if (i > 0 && servers[i] != servers[i - 1]) {
                inefficiency++;
            }
        }

        return inefficiency;
    }

    public static void main(String[] args) {
        MinimumInefficiency solution = new MinimumInefficiency();
        System.out.println(solution.findMinimumInefficiency("00?10??1?")); // 输出: 2
    }
}
