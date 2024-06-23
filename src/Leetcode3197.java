public class Leetcode3197 {
  public int minimumSum(int[][] grid) {
    int rows = grid.length;
    int cols = grid[0].length;
    int minSum = Integer.MAX_VALUE;

    for (int c1 = 1; c1 < cols; c1++) {
      for (int c2 = c1 + 1; c2 < cols; c2++) {
        int area1 = calculateArea(grid, 0, rows, 0, c1);
        int area2 = calculateArea(grid, 0, rows, c1, c2);
        int area3 = calculateArea(grid, 0, rows, c2, cols);
        if (isValidPartition(grid, area1, area2, area3)) {
          int sum = area1 + area2 + area3;
          minSum = Math.min(minSum, sum);
        }
      }
      for (int r2 = 1; r2 < rows; r2++) {
        int area1 = calculateArea(grid, 0, rows, 0, c1);
        int area2 = calculateArea(grid, 0, r2, c1, cols);
        int area3 = calculateArea(grid, r2, rows, c1, cols);
        if (isValidPartition(grid, area1, area2, area3)) {
          int sum = area1 + area2 + area3;
          minSum = Math.min(minSum, sum);
        }
      }
    }

    for (int c1 = cols - 1; c1 > 0; c1--) {
      for (int r2 = 1; r2 < rows; r2++) {
        int area1 = calculateArea(grid, 0, rows, c1, cols);
        int area2 = calculateArea(grid, 0, r2, 0, c1);
        int area3 = calculateArea(grid, r2, rows, 0, c1);
        if (isValidPartition(grid, area1, area2, area3)) {
          int sum = area1 + area2 + area3;
          minSum = Math.min(minSum, sum);
        }
      }
    }

    for (int r1 = 1; r1 < rows; r1++) {
      for (int r2 = r1 + 1; r2 < rows; r2++) {
        int area1 = calculateArea(grid, 0, r1, 0, cols);
        int area2 = calculateArea(grid, r1, r2, 0, cols);
        int area3 = calculateArea(grid, r2, rows, 0, cols);
        if (isValidPartition(grid, area1, area2, area3)) {
          int sum = area1 + area2 + area3;
          minSum = Math.min(minSum, sum);
        }
      }
      for (int c2 = 1; c2 < cols; c2++) {
        int area1 = calculateArea(grid, 0, r1, 0, cols);
        int area2 = calculateArea(grid, r1, rows, 0, c2);
        int area3 = calculateArea(grid, r1, rows, c2, cols);
        if (isValidPartition(grid, area1, area2, area3)) {
          int sum = area1 + area2 + area3;
          minSum = Math.min(minSum, sum);
        }
      }
    }

    for (int r1 = rows - 1; r1 > 0; r1--) {
      for (int c2 = 1; c2 < cols; c2++) {
        int area1 = calculateArea(grid, r1, rows, 0, cols);
        int area2 = calculateArea(grid, 0, r1, 0, c2);
        int area3 = calculateArea(grid, 0, r1, c2, cols);
        if (isValidPartition(grid, area1, area2, area3)) {
          int sum = area1 + area2 + area3;
          minSum = Math.min(minSum, sum);
        }
      }
    }
    return minSum;
  }

  public int calculateArea(int[][] grid, int r1, int r2, int c1, int c2) {
    int l = r2;
    int r = r1;
    int u = c2;
    int d = c1;
    boolean hasOne = false;
    for(int i=r1;i<r2;i++){
      for(int j=c1;j<c2;j++){
        if(grid[i][j] == 1){
          l = Math.min(l, i);
          r = Math.max(r, i);
          u = Math.min(u, j);
          d = Math.max(d, j);
          hasOne = true;
        }
      }
    }
    return hasOne ? (r-l+1)*(d-u+1) : 0;
  }

  private boolean isValidPartition(int[][] grid, int area1, int area2, int area3) {
    int nonZeroCount = 0;
    if (area1 > 0) nonZeroCount++;
    if (area2 > 0) nonZeroCount++;
    if (area3 > 0) nonZeroCount++;
    return nonZeroCount == 3;
  }
}
