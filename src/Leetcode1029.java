import java.util.Arrays;

public class Leetcode1029 {
  public int twoCitySchedCost(int[][] costs) {
    Arrays.sort(costs, (a, b) -> Math.abs(b[0] - b[1]) - Math.abs(a[0] - a[1]));
    int totalCost = 0;
    int n = costs.length / 2;
    int countA = 0, countB = 0;
    for (int i = 0; i < costs.length; i++) {
      if ((countA < n && costs[i][0] <= costs[i][1]) || countB == n) {
        totalCost += costs[i][0];
        countA++;
      } else {
        totalCost += costs[i][1];
        countB++;
      }
    }
    return totalCost;
  }
}
