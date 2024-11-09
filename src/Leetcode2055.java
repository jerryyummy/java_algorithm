import java.util.ArrayList;
import java.util.List;

public class Leetcode2055 {
  public List<Integer> platesBetweenCandles(String s, int[][] queries) {
    List<Integer> candlePositions = new ArrayList<>(); // 用于存储蜡烛的位置
    List<Integer> result = new ArrayList<>(); // 结果列表

    // 找到所有蜡烛的位置
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '|') {
        candlePositions.add(i);
      }
    }

    // 处理每个查询
    for (int[] query : queries) {
      int left = query[0];
      int right = query[1];

      // 在 candlePositions 中找到 >= left 的第一个位置
      int i = lowerBound(candlePositions, left);
      // 在 candlePositions 中找到 <= right 的最后一个位置
      int j = upperBound(candlePositions, right) - 1;

      // 如果 i < j，则可以计算出蜡烛之间的盘子数量
      if (i < j) {
        result.add(candlePositions.get(j) - candlePositions.get(i) - (j - i));
      } else {
        result.add(0); // 如果没有有效的蜡烛范围，则结果为 0
      }
    }

    return result;
  }

  // 模拟 lower_bound 函数：找到第一个大于或等于 target 的位置
  private int lowerBound(List<Integer> list, int target) {
    int left = 0, right = list.size();
    while (left < right) {
      int mid = left + (right - left) / 2;
      if (list.get(mid) >= target) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }
    return left;
  }

  // 模拟 upper_bound 函数：找到第一个大于 target 的位置
  private int upperBound(List<Integer> list, int target) {
    int left = 0, right = list.size();
    while (left < right) {
      int mid = left + (right - left) / 2;
      if (list.get(mid) > target) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }
    return left;
  }

}
