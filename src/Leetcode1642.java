import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Leetcode1642 {
  public int furthestBuilding(int[] heights, int bricks, int ladders) {
    Queue<Integer> ladderAllocations = new PriorityQueue<>();
    for (int i = 0; i < heights.length - 1; i++) {
      int climb = heights[i + 1] - heights[i];
      if (climb <= 0) {
        continue;
      }
      ladderAllocations.add(climb);
      if (ladderAllocations.size() <= ladders) {
        continue;
      }
      bricks -= ladderAllocations.remove();
      if (bricks < 0) {
        return i;
      }
    }
    return heights.length - 1;
  }

}
