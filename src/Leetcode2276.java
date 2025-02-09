import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Leetcode2276 {
  private TreeMap<Integer, Integer> map;
  private int totalCount;

  public Leetcode2276() {
    map = new TreeMap<>();
    totalCount = 0;
  }

  public void add(int left, int right) {
    // 找到第一个不晚于 `left` 开始的区间
    Map.Entry<Integer, Integer> lower = map.floorEntry(right);

    // 合并区间
    while (lower != null && lower.getValue() >= left) {
      left = Math.min(left, lower.getKey()); // 扩展左边界
      right = Math.max(right, lower.getValue()); // 扩展右边界
      totalCount -= (lower.getValue() - lower.getKey() + 1); // 减去被合并的区间长度
      map.remove(lower.getKey()); // 移除已合并的区间
      lower = map.floorEntry(right);
    }

    // 添加合并后的新区间
    map.put(left, right);
    totalCount += (right - left + 1);
  }

  public int count() {
    return totalCount;
  }
}
