import java.util.Arrays;

public class Leetcode135 {
  public int candy(int[] ratings) {
    if (ratings == null || ratings.length == 0) return 0;
    int[] candies = new int[ratings.length];
    Arrays.fill(candies, 1); // Each child has at least one candy initially

    // Left to Right pass
    for (int i = 1; i < ratings.length; i++) {
      if (ratings[i] > ratings[i - 1]) {
        candies[i] = candies[i - 1] + 1;
      }
    }

    // Right to Left pass
    for (int i = ratings.length - 2; i >= 0; i--) {
      if (ratings[i] > ratings[i + 1]) {
        candies[i] = Math.max(candies[i], candies[i + 1] + 1);
      }
    }

    // Sum up the candies
    int result = 0;
    for (int candy : candies) {
      result += candy;
    }

    return result;
  }
}
