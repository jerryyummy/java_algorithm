package shopee;

import java.util.ArrayList;
import java.util.List;

public class HeroPurchaser {

  public static void main(String[] args) {
    int[] heroCosts = {4, 5, 1, 3, 2, 8};
    int totalMoney = 10;

    List<Integer> purchasedHeroes = maximizeHeroes(heroCosts, totalMoney);
    System.out.println("Maximum heroes purchased: " + purchasedHeroes.size());
    System.out.println("Indices of heroes purchased: " + purchasedHeroes);
  }

  public static List<Integer> maximizeHeroes(int[] costs, int money) {
    int n = costs.length;
    int[][] dp = new int[n+1][money+1];
    boolean[][] choice = new boolean[n+1][money+1];

    for (int i = 1; i <= n; i++) {
      for (int j = 0; j <= money; j++) {
        dp[i][j] = dp[i-1][j];  // not buying the hero
        if (j >= costs[i-1]) {
          if (dp[i][j] < 1 + dp[i-1][j-costs[i-1]]) {
            dp[i][j] = 1 + dp[i-1][j-costs[i-1]];
            choice[i][j] = true;
          }
        }
      }
    }

    // Determining which heroes were bought
    List<Integer> heroesBought = new ArrayList<>();
    int remainingMoney = money;
    for (int i = n; i > 0; i--) {
      if (choice[i][remainingMoney]) {
        heroesBought.add(i-1); // Add hero index (0-based)
        remainingMoney -= costs[i-1];
      }
    }

    return heroesBought;
  }

}