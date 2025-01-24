import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class Leetcode1244 {
  private Map<Integer, Integer> scores;

  public Leetcode1244() {
    scores = new HashMap<>();
  }

  public void addScore(int playerId, int score) {

    if (!this.scores.containsKey(playerId)) {
      this.scores.put(playerId, 0);
    }

    this.scores.put(playerId, this.scores.get(playerId) + score);
  }

  public int top(int K) {
    PriorityQueue<Entry<Integer, Integer>> heap = new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());

    for (Map.Entry<Integer, Integer> entry : this.scores.entrySet()) {
      heap.offer(entry);
      if (heap.size() > K) {
        heap.poll();
      }
    }

    int total = 0;
    Iterator value = heap.iterator();
    while (value.hasNext()) {
      total += ((Map.Entry<Integer, Integer>)value.next()).getValue();
    }

    return total;
  }

  public void reset(int playerId) {
    this.scores.put(playerId, 0);
  }
}
