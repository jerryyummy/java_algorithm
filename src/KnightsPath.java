import java.util.LinkedList;
import java.util.Queue;

public class KnightsPath {

    // Directions a knight can move; 8 possible moves
    private static final int[][] moves = {
            {-2, -1}, {-1, -2}, {1, -2}, {2, -1},
            {2, 1}, {1, 2}, {-1, 2}, {-2, 1}
    };

    // Check if the given cell is threatened by the bishop
    private static boolean isThreatened(int x, int y, int bishopRow, int bishopCol) {
        return Math.abs(bishopRow - x) == Math.abs(bishopCol - y);
    }

    public static int minimumKnightMoves(int n, int startRow, int startCol, int endRow, int endCol, int bishopRow, int bishopCol) {
        // To keep track of visited squares with state [row, col, bishopCaptured]
        boolean[][][] visited = new boolean[n][n][2];

        Queue<int[]> queue = new LinkedList<>();
        // Add starting position to the queue, with bishop not captured
        queue.add(new int[]{startRow, startCol, 0, 0}); // [row, col, moves, bishopCaptured]

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            int moveCount = current[2];
            int bishopCaptured = current[3];

            // Check if reached the end position, return the move count
            if (row == endRow && col == endCol) {
                return moveCount;
            }

            // Try all possible moves for the knight
            for (int[] move : moves) {
                int newRow = row + move[0];
                int newCol = col + move[1];

                // Check if new position is on the board and not visited
                if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < n && !visited[newRow][newCol][bishopCaptured]) {
                    // Check if moving to this cell is either capturing the bishop or not threatened
                    if ((newRow == bishopRow && newCol == bishopCol) || !isThreatened(newRow, newCol, bishopRow, bishopCol) || (newRow == endRow && newCol == endCol)) {
                        queue.add(new int[]{newRow, newCol, moveCount + 1, bishopCaptured | (newRow == bishopRow && newCol == bishopCol ? 1 : 0)});
                        visited[newRow][newCol][bishopCaptured] = true;
                    }
                }
            }
        }

        // If no path is found
        return -1;
    }

    public static void main(String[] args) {
        int n = 8; // size of the chessboard
        int startRow = 4;
        int startCol = 2;
        int endRow = 2;
        int endCol = 6;
        int bishopRow = 2;
        int bishopCol = 3;

        int result = minimumKnightMoves(n, startRow, startCol, endRow, endCol, bishopRow, bishopCol);
        System.out.println(result); // Expected output: 3
    }
}

