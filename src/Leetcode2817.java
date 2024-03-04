import java.util.List;
import java.util.TreeSet;

public class Leetcode2817 {
  public int minAbsoluteDifference(List<Integer> nums, int x) {
    int[] a = nums.stream().mapToInt(i -> i).toArray();
    int ans = Integer.MAX_VALUE, n = a.length;
    TreeSet<Integer> s = new TreeSet<Integer>();
    s.add(Integer.MAX_VALUE); // 哨兵
    s.add(Integer.MIN_VALUE / 2);
    for (int i = x; i < n; i++) {
      s.add(a[i - x]);
      int y = a[i];
      ans = Math.min(ans, Math.min(s.ceiling(y) - y, y - s.floor(y)));
    }
    return ans;
  }
}
