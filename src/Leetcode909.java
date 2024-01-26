import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Leetcode909 {
  public int snakesAndLadders(int[][] board) {
    int n = board.length;
    Queue<Integer> queue = new LinkedList<>();
    queue.add(1); // Start from the first cell
    int step = 0;
    boolean[] visited = new boolean[n * n + 1];
    visited[1] = true;

    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        int cur = queue.remove();
        if (cur == n * n) return step;

        for (int j = 1; j <= 6 && cur + j <= n * n; j++) {
          int next = cur + j;
          int[] pos = findPosition(next, n);
          if (board[pos[0]][pos[1]] != -1) {
            next = board[pos[0]][pos[1]];
          }

          if (!visited[next]) {
            visited[next] = true;
            queue.add(next);
          }
        }
      }
      step++;
    }
    return -1;
  }

  private int[] findPosition(int number, int n) {
    int row = (number - 1) / n;
    int col = (number - 1) % n;
    if (row % 2 == 1) {
      col = n - 1 - col;
    }
    return new int[]{n - 1 - row, col};
  }
}
