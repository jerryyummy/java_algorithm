import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Leetcode554 {
    public int leastBricks(List<List<Integer>> wall) {
        if (wall == null || wall.isEmpty()) return 0;

        Map<Integer, Integer> cnt = new HashMap<>();
        int maxEdges = 0;

        for (List<Integer> row : wall) {
            int sum = 0;
            // 只统计到倒数第二块砖的右边界
            for (int i = 0; i < row.size() - 1; i++) {
                sum += row.get(i);
                int c = cnt.getOrDefault(sum, 0) + 1;
                cnt.put(sum, c);
                maxEdges = Math.max(maxEdges, c);
            }
        }
        // 最少穿过砖块数 = 总行数 - 最多对齐的缝数
        return wall.size() - maxEdges;
    }
}
