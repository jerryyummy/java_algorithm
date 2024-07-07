public class Leetcode3207 {
  public long maximumPoints(int[] enemyEnergies, int currentEnergy) {
    long profit = 0;
    long mn = Integer.MAX_VALUE;
    long totalEnergy = currentEnergy;

    for (int i = 0; i < enemyEnergies.length; i++) {
      if (enemyEnergies[i] < mn) {
        mn = enemyEnergies[i];
      }
      totalEnergy += enemyEnergies[i];
    }

    if (currentEnergy < mn) {
      return 0;
    }

    totalEnergy -= mn;
    return totalEnergy / mn;
  }
}
