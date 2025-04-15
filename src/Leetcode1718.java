import java.util.*;

public class Leetcode1718 {
    int[] res;
    boolean[] used;
    boolean found = false;
    int size;

    public int[] constructDistancedSequence(int n) {
        size = n;
        res = new int[2 * n - 1];
        used = new boolean[n + 1]; // 标记当前数字是否已放完（true 表示已放完）
        dfs(0);
        return res;
    }

    public void dfs(int idx) {
        if (found) return;

        // 找到结果
        if (idx == res.length) {
            found = true;
            return;
        }

        // 当前 idx 已填，跳过
        if (res[idx] != 0) {
            dfs(idx + 1);
            return;
        }

        // 尝试从大到小填入数字
        for (int num = size; num >= 1; num--) {
            if (used[num]) continue;

            // 特判数字1：只能出现一次
            if (num == 1) {
                res[idx] = 1;
                used[1] = true;
                dfs(idx + 1);
                if (found) return;
                res[idx] = 0;
                used[1] = false;
            } else {
                // 数字num，尝试填 idx 和 idx+num
                int j = idx + num;
                if (j < res.length && res[j] == 0) {
                    res[idx] = res[j] = num;
                    used[num] = true;
                    dfs(idx + 1);
                    if (found) return;
                    res[idx] = res[j] = 0;
                    used[num] = false;
                }
            }
        }
    }
}
