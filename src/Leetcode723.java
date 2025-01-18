import java.util.HashSet;
import java.util.Set;

public class Leetcode723 {
  private int m, n;

  record Pair(int row, int col) {}

  private Set<Pair> find(int[][] board) {
    Set<Pair> crushedSet = new HashSet<>();

    // Check vertically adjacent candies
    for (int r = 1; r < m - 1; r++) {
      for (int c = 0; c < n; c++) {
        if (board[r][c] == 0) {
          continue;
        }
        if (board[r][c] == board[r - 1][c] && board[r][c] == board[r + 1][c]) {
          crushedSet.add(new Pair(r, c));
          crushedSet.add(new Pair(r - 1, c));
          crushedSet.add(new Pair(r + 1, c));
        }
      }
    }

    // Check horizontally adjacent candies
    for (int r = 0; r < m; r++) {
      for (int c = 1; c < n - 1; c++) {
        if (board[r][c] == 0) {
          continue;
        }
        if (board[r][c] == board[r][c - 1] && board[r][c] == board[r][c + 1]) {
          crushedSet.add(new Pair(r, c));
          crushedSet.add(new Pair(r, c - 1));
          crushedSet.add(new Pair(r, c + 1));
        }
      }
    }

    return crushedSet;
  }

  private void crush(int[][] board, Set<Pair> crushedSet) {
    for (Pair pair : crushedSet) {
      board[pair.row()][pair.col()] = 0;
    }
  }

  private void drop(int[][] board) {
    for (int c = 0; c < n; c++) {
      int lowestZero = -1;
      for (int r = m - 1; r >= 0; r--) {
        if (board[r][c] == 0) {
          lowestZero = Math.max(lowestZero, r);
        } else if (lowestZero >= 0) {
          board[lowestZero][c] = board[r][c];
          board[r][c] = 0;
          lowestZero--;
        }
      }
    }
  }

  public int[][] candyCrush(int[][] board) {
    m = board.length;
    n = board[0].length;
    Set<Pair> crushedSet = find(board);
    while (!crushedSet.isEmpty()) {
      crush(board, crushedSet);
      drop(board);
      crushedSet = find(board);
    }

    return board;
  }
}
