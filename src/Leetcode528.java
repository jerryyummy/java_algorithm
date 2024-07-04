import java.util.Random;

public class Leetcode528 {
  private int[] prefixSums;
  private int totalSum;

  public Leetcode528(int[] w) {
    this.prefixSums = new int[w.length];

    int prefixSum = 0;
    for (int i = 0; i < w.length; ++i) {
      prefixSum += w[i];
      this.prefixSums[i] = prefixSum;
    }
    this.totalSum = prefixSum;
  }

  public int pickIndex() {
    double target = this.totalSum * Math.random();
    int i = 0;
    // run a linear search to find the target zone
    for (; i < this.prefixSums.length; ++i) {
      if (target < this.prefixSums[i])
        return i;
    }
    // to have a return statement, though this should never happen.
    return i - 1;
  }
}
