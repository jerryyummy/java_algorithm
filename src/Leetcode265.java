public class Leetcode265 {
  /*
  public int minCostII(int[][] costs) {

    if (costs.length == 0) return 0;
    int k = costs[0].length;
    int n = costs.length;

    int[] previousRow = costs[0];

    for (int house = 1; house < n; house++) {
      int[] currentRow = new int[k];
      for (int color = 0; color < k; color++) {
        int min = Integer.MAX_VALUE;
        for (int previousColor = 0; previousColor < k; previousColor++) {
          if (color == previousColor) continue;
          min = Math.min(min, previousRow[previousColor]);
        }
        currentRow[color] += costs[house][color] += min;
      }
      previousRow = currentRow;
    }

    // Find the minimum in the last row.
    int min = Integer.MAX_VALUE;
    for (int c : previousRow) {
      min = Math.min(min, c);
    }
    return min;
  }
   */

  /*
  public int minCostII(int[][] costs) {

    if (costs.length == 0) return 0;
    int k = costs[0].length;
    int n = costs.length;

    for (int house = 1; house < n; house++) {

      // Find the minimum and second minimum color in the PREVIOUS row.
      int minColor = -1; int secondMinColor = -1;
      for (int color = 0; color < k; color++) {
        int cost = costs[house - 1][color];
        if (minColor == -1 || cost < costs[house - 1][minColor]) {
          secondMinColor = minColor;
          minColor = color;
        } else if (secondMinColor == -1 || cost < costs[house - 1][secondMinColor]) {
          secondMinColor = color;
        }
      }

      // And now calculate the new costs for the current row.
      for (int color = 0; color < k; color++) {
        if (color == minColor) {
          costs[house][color] += costs[house - 1][secondMinColor];
        } else {
          costs[house][color] += costs[house - 1][minColor];
        }
      }
    }

    // Find the minimum in the last row.
    int min = Integer.MAX_VALUE;
    for (int c : costs[n - 1]) {
      min = Math.min(min, c);
    }
    return min;
  }
   */

  public int minCostII(int[][] costs) {

    if (costs.length == 0) return 0;
    int k = costs[0].length;
    int n = costs.length;


    /* Firstly, we need to determine the 2 lowest costs of
     * the first row. We also need to remember the color of
     * the lowest. */
    int prevMin = -1; int prevSecondMin = -1; int prevMinColor = -1;
    for (int color = 0; color < k; color++) {
      int cost = costs[0][color];
      if (prevMin == -1 || cost < prevMin) {
        prevSecondMin = prevMin;
        prevMinColor = color;
        prevMin = cost;
      } else if (prevSecondMin == -1 || cost < prevSecondMin) {
        prevSecondMin = cost;
      }
    }

    // And now, we need to work our way down, keeping track of the minimums.
    for (int house = 1; house < n; house++) {
      int min = -1; int secondMin = -1; int minColor = -1;
      for (int color = 0; color < k; color++) {
        // Determine the cost for this cell (without writing it in).
        int cost = costs[house][color];
        if (color == prevMinColor) {
          cost += prevSecondMin;
        } else {
          cost += prevMin;
        }
        // Determine whether or not this current cost is also a minimum.
        if (min == -1 || cost < min) {
          secondMin = min;
          minColor = color;
          min = cost;
        } else if (secondMin == -1 || cost < secondMin) {
          secondMin = cost;
        }
      }
      // Transfer current mins to be previous mins.
      prevMin = min;
      prevSecondMin = secondMin;
      prevMinColor = minColor;
    }

    return prevMin;
  }
}
