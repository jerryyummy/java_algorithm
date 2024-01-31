import java.util.HashMap;
import java.util.Map;

public class Leetcode403 {
  /*
  Map<Integer, Integer> map = new HashMap<>();
  // int[][] cache = new int[2009][2009];
  Map<String, Boolean> cache = new HashMap<>();
  public boolean canCross(int[] stones) {
    int n = stones.length;
    for (int i = 0; i < n; i++) {
      map.put(stones[i], i);//存储石头的位置和索引
    }
    // check first step
    if (!map.containsKey(1)) return false;//如果不能第一步掉到1，直接false
    return dfs(stones, stones.length, 1, 1);
  }
  boolean dfs(int[] stone, int n, int position, int step) {
    String key = position + "_" + step;
    if (cache.containsKey(key)) return cache.get(key);
    if (position == n - 1) return true;//到达最后一个石头
    for (int i = -1; i <= 1; i++) {
      if (step + i == 0) continue;//不能原地不动
      int next = stone[position] + step + i;
      if (map.containsKey(next)) {//如果这个位置有石头
        boolean cur = dfs(stone, n, map.get(next), step + i);
        cache.put(key, cur);
        if (cur) return true;
      }
    }
    cache.put(key, false);
    return false;
  }
   */

  public boolean canCross(int[] stones) {
    int n = stones.length;
    if (stones[1]!=1) return false;
    boolean[][] dp = new boolean[n+1][n+1];
    dp[1][1] = true;

    for (int i = 2; i < n; i++) {
      for (int j = 1; j < i; j++) {
        int distance = stones[i]-stones[j];
        if (distance<=j+1){//确保可以跳到
          dp[i][distance] = dp[j][distance-1]||dp[j][distance]||dp[j][distance+1];
        }
      }
    }

    for (int i = 1; i < n; i++) {
      if (dp[n-1][i]) return true;
    }
    return false;
  }

}
