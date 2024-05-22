public class Leetcode256 {
  public int minCost(int[][] cost){
    int n = cost.length;
    for (int i = 1; i < n; i++) {
      cost[i][0] += Math.min(cost[i-1][1], cost[i-1][2]);
      cost[i][1] += Math.min(cost[i-1][0], cost[i-1][2]);
      cost[i][2] += Math.min(cost[i-1][0], cost[i-1][1]);
    }
    return Math.min(Math.min(cost[n-1][0], cost[n-1][1]),cost[n-1][2]);
  }
}
