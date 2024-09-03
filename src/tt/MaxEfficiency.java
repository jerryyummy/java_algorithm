package tt;

import java.util.Arrays;

public class MaxEfficiency {
  public static long maximizeEfficiencyProduct(int[] efficiencyScores) {
    // Sort the efficiency scores
    Arrays.sort(efficiencyScores);

    int n = efficiencyScores.length;

    // Calculate the maximum possible product for the five largest values
    long maxFive = (long) efficiencyScores[n - 1]
        * efficiencyScores[n - 2]
        * efficiencyScores[n - 3]
        * efficiencyScores[n - 4]
        * efficiencyScores[n - 5];

    // Calculate the maximum possible product for three largest and two smallest values (considering negative numbers)
    long maxThreeTwo = (long) efficiencyScores[0]
        * efficiencyScores[1]
        * efficiencyScores[n - 1]
        * efficiencyScores[n - 2]
        * efficiencyScores[n - 3];

    // Calculate the maximum possible product for the largest value and four smallest values (considering negative numbers)
    long maxOneFour = (long) efficiencyScores[0]
        * efficiencyScores[1]
        * efficiencyScores[2]
        * efficiencyScores[3]
        * efficiencyScores[n - 1];

    // Return the maximum product from the above three possibilities
    return Math.max(maxFive, Math.max(maxThreeTwo, maxOneFour));
  }

  public static void main(String[] args) {
    int[] efficiencyScores = {6, 1, 2, 8, 1};
    System.out.println(maximizeEfficiencyProduct(efficiencyScores));
  }
}

