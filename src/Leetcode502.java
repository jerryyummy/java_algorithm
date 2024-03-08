import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Leetcode502 {
  public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
    // Projects available for investment, sorted by required capital.
    PriorityQueue<int[]> availableProjects = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
    // Profitable projects that can be afforded, sorted by profit.
    PriorityQueue<int[]> profitableProjects = new PriorityQueue<>((a, b) -> b[1] - a[1]);

    // Initialize the available projects.
    for (int i = 0; i < profits.length; i++) {
      availableProjects.offer(new int[]{capital[i], profits[i]});
    }

    // Current capital.
    int cur = w;

    // For up to k rounds, invest in the most profitable project you can afford.
    for (int i = 0; i < k; i++) {
      // Move projects that can now be afforded to the profitable queue.
      while (!availableProjects.isEmpty() && availableProjects.peek()[0] <= cur) {
        profitableProjects.add(availableProjects.poll());
      }

      // If there are no affordable projects, break.
      if (profitableProjects.isEmpty()) {
        break;
      }

      // Invest in the most profitable project.
      cur += profitableProjects.poll()[1];
    }

    return cur;
  }
}
