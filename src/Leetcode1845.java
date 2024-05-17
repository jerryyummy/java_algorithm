import java.util.PriorityQueue;

public class Leetcode1845 {
  PriorityQueue<Integer> queue;
  public Leetcode1845(int n) {
    queue = new PriorityQueue<>();
    for (int i = 1; i <= n; i++) {
      queue.add(i);
    }
  }

  public int reserve() {
    return queue.remove();
  }

  public void unreserve(int seatNumber) {
    queue.add(seatNumber);
  }
}
