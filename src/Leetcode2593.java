public class Leetcode2593 {
  public long findScore(int[] numbers) {
    long ans = 0;
    for (int i = 0; i < numbers.length; i += 2) {
      int currentStart = i;
      while (i + 1 < numbers.length && numbers[i + 1] < numbers[i]) {
        i++;
      }
      for (
          int currentIndex = i;
          currentIndex >= currentStart;
          currentIndex -= 2
      ) {
        ans += numbers[currentIndex];
      }
    }
    return ans;
  }
}
