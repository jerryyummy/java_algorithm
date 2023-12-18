public class Leetcode695 {
    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int currentArea = dfs(grid, i, j);
                    maxArea = Math.max(maxArea, currentArea);
                }
            }
        }
        return maxArea;
    }

    public int dfs(int[][] grid, int row, int column) {
        int m = grid.length;
        int n = grid[0].length;
        if (row < 0 || row >= m || column < 0 || column >= n || grid[row][column] == 0) {
            return 0;
        }
        grid[row][column] = 0;
        int area = 1; // Starting area for this part of the island
        area += dfs(grid, row - 1, column); // Add area from the top cell
        area += dfs(grid, row + 1, column); // Add area from the bottom cell
        area += dfs(grid, row, column - 1); // Add area from the left cell
        area += dfs(grid, row, column + 1); // Add area from the right cell
        return area;
    }

}
