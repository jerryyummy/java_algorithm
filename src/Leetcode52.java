import java.util.HashSet;
import java.util.Set;

public class Leetcode52 {
  private int size;

  private int res = 0;
  public int totalNQueens(int n) {
    size = n;
    backtrack(0, new HashSet<>(), new HashSet<>(), new HashSet<>());
    return res;
  }

  private void backtrack(int row, Set<Integer> diagonals, Set<Integer> antiDiagonals, Set<Integer> cols) {
    // Base case - N queens have been placed
    if (row == size) {
      res++;
      return;
    }

    for (int col = 0; col < size; col++) {
      int currDiagonal = row - col;
      int currAntiDiagonal = row + col;
      // If the queen is not placeable
      if (cols.contains(col) || diagonals.contains(currDiagonal) || antiDiagonals.contains(currAntiDiagonal)) {
        continue;
      }

      // "Add" the queen to the board
      cols.add(col);
      diagonals.add(currDiagonal);
      antiDiagonals.add(currAntiDiagonal);

      // Move on to the next row with the updated board state
      backtrack(row + 1, diagonals, antiDiagonals, cols);

      // "Remove" the queen from the board since we have already
      // explored all valid paths using the above function call
      cols.remove(col);
      diagonals.remove(currDiagonal);
      antiDiagonals.remove(currAntiDiagonal);
    }
  }

  public static void main(String[] args) {
    Leetcode52 solution = new Leetcode52();
    System.out.println(solution.totalNQueens(6));
  }

}
