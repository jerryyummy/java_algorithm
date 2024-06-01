import java.util.HashMap;

public class Leetcode1473 {

  Integer[][][] memo = new Integer[100][100][21];
  // Maximum cost possible plus 1
  final int MAX_COST = 1000001;

  int findMinCost(int[] houses, int[][] cost, int targetCount, int currIndex,
      int neighborhoodCount, int prevHouseColor) {
    if (currIndex == houses.length) {
      return neighborhoodCount == targetCount ? 0 : MAX_COST;
    }

    if (neighborhoodCount > targetCount) {
      return MAX_COST;
    }

    if (memo[currIndex][neighborhoodCount][prevHouseColor] != null) {
      return memo[currIndex][neighborhoodCount][prevHouseColor];
    }

    int minCost = MAX_COST;
    if (houses[currIndex] != 0) {
      int newNeighborhoodCount = neighborhoodCount + (houses[currIndex] != prevHouseColor ? 1 : 0);
      minCost = findMinCost(houses, cost, targetCount, currIndex + 1, newNeighborhoodCount, houses[currIndex]);
    } else {
      int totalColors = cost[0].length;
      // If the house is not painted, try every possible color and store the minimum cost
      for (int color = 1; color <= totalColors; color++) {
        int newNeighborhoodCount = neighborhoodCount + (color != prevHouseColor ? 1 : 0);
        int currCost = cost[currIndex][color - 1]
            + findMinCost(houses, cost, targetCount, currIndex + 1, newNeighborhoodCount, color);
        minCost = Math.min(minCost, currCost);
      }
    }

    return memo[currIndex][neighborhoodCount][prevHouseColor] = minCost;
  }

  public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
    int answer = findMinCost(houses, cost, target, 0, 0, 0);
    return answer == MAX_COST ? -1 : answer;
  }
}
