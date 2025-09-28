import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Leetcode2021 {
    public int brightestPosition(int[][] lights) {
        // 差分：key=坐标, val=亮度增量
        TreeMap<Long, Integer> diff = new TreeMap<>();
        for (int[] l : lights) {
            long L = (long) l[0] - l[1]; // 左端
            long R = (long) l[0] + l[1]; // 右端（闭区间）
            diff.put(L, diff.getOrDefault(L, 0) + 1);
            diff.put(R + 1, diff.getOrDefault(R + 1, 0) - 1); // 右端+1处-1
        }

        long bestPos = 0;
        int cur = 0, best = Integer.MIN_VALUE;
        for (Map.Entry<Long, Integer> e : diff.entrySet()) {
            cur += e.getValue();
            if (cur > best) {
                best = cur;
                bestPos = e.getKey(); // 最早达到最大亮度的位置
            }
        }
        return (int) bestPos; // 题目坐标范围在 int 内
    }
}
