import java.util.HashMap;
import java.util.Map;

public class Leetcode3154 {
  public int waysToReachStair(int k) {
    return dfs(1, 0, 0, k, new HashMap<>());
  }

  private int dfs(int i, int j, int preDown, int k, Map<Long, Integer> memo) {
    if (i > k + 1) {
      return 0;
    }
    long p = ((long) i << 32) | j << 1 | preDown; // 用一个 long 表示状态
    if (memo.containsKey(p)) { // 之前算过了
      return memo.get(p);
    }
    int res = i == k ? 1 : 0;
    res += dfs(i + (1 << j), j + 1, 0, k, memo); // 操作二
    if (preDown == 0 && i > 0) {
      res += dfs(i - 1, j, 1, k, memo); // 操作一
    }
    memo.put(p, res); // 记忆化
    return res;
  }

}
