import java.util.Arrays;

public class Leetcode1423 {
  public int maxScore(int[] cardPoints, int k) {
    int sum = Arrays.stream(cardPoints).sum();
    int i = 0, j = cardPoints.length-k;
    int cur = Arrays.stream(Arrays.copyOf(cardPoints,j)).sum();
    int res = sum-cur;
    while (j < cardPoints.length){
      cur -= cardPoints[i];
      i++;
      cur += cardPoints[j];
      res = Math.max(res, sum - cur);
      j++;
    }

    return res;
  }
}
