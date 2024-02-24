import java.util.ArrayList;
import java.util.List;

public class Leetcode57 {
  public int[][] insert(int[][] intervals, int[] newInterval) {
    List<int[]> res = new ArrayList<>();
    int i = 0;
    while (i < intervals.length && intervals[i][1] < newInterval[0]) {
      res.add(intervals[i]);
      i++;
    }

    int[] mergeInterval = new int[]{newInterval[0], newInterval[1]};
    if (i < intervals.length) {
      mergeInterval[0] = Math.min(newInterval[0], intervals[i][0]);
    }

    int j = i;
    while (j < intervals.length && intervals[j][0] <= newInterval[1]) {
      mergeInterval[1] = Math.max(newInterval[1], intervals[j][1]);
      j++;
    }

    res.add(mergeInterval);

    while (j < intervals.length) {
      res.add(intervals[j]);
      j++; // 修正无限循环问题
    }

    return res.toArray(new int[res.size()][2]); // 修正转换结果列表为数组的语法

  }
}
