public class Leetcode1823 {
  public int findTheWinner(int n, int k) {
    return winnerHelper(n, k) + 1;
  }

  private int winnerHelper(int n, int k) {
    if (n == 1) {
      return 0;
    }
    return (winnerHelper(n - 1, k) + k) % n;
  }
}
