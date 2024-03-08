public class Leetcode201 {
  public int rangeBitwiseAnd(int left, int right) {
    while (left < right) {
      // 抹去最右边的 1
      right = right & (right - 1);
    }
    return right;
  }
}
