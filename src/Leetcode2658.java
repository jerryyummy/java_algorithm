import java.util.LinkedList;
import java.util.Queue;

public class Leetcode2658 {
    public int findMaxFish(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0) {
                    int cur = findMaxFish(i,j,grid);
                    if (cur > res) {
                        res = cur;
                    }
                }
            }
        }
        return res;
    }

    public int findMaxFish(int rowId, int colId, int[][] grid) {
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{rowId, colId});
        int res = grid[rowId][colId];
        grid[rowId][colId] = 0;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int row = cur[0];
            int col = cur[1];
            for (int[] direction : directions) {
                int newRow = row + direction[0];
                int newCol = col + direction[1];
                if (newRow>=0 && newRow< grid.length && newCol>=0 && newCol< grid[0].length && grid[newRow][newCol]>0) {
                    res += grid[newRow][newCol];
                    grid[newRow][newCol] = 0;
                    q.add(new int[]{newRow, newCol});

                }
            }
        }
        return res;
    }
}
