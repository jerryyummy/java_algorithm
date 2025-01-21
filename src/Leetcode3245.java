import java.util.ArrayDeque;
import java.util.Deque;

public class Leetcode3245 {
  public int[] resultsArray(int[] nums, int k) {
    int length = nums.length;
    int[] result = new int[length - k + 1];
    Deque<Integer> indexDeque = new ArrayDeque<>();

    for (int currentIndex = 0; currentIndex < length; currentIndex++) {
      if (!indexDeque.isEmpty() && indexDeque.peekFirst() < currentIndex - k + 1) {
        indexDeque.pollFirst();
      }

      if (!indexDeque.isEmpty() && nums[currentIndex] != nums[currentIndex - 1] + 1) {
        indexDeque.clear();
      }

      indexDeque.offerLast(currentIndex);

      if (currentIndex >= k - 1) {
        if (indexDeque.size() == k) {
          result[currentIndex - k + 1] = nums[indexDeque.peekLast()];
        } else {
          result[currentIndex - k + 1] = -1;
        }
      }
    }

    return result;
  }
}
