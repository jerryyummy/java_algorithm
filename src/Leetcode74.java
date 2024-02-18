public class Leetcode74 {
  public boolean searchMatrix(int[][] matrix, int target) {
    int m = matrix.length;
    int n = matrix[0].length;

    int left = 0, right = m * n - 1;
    int pivotIdx, pivotElement;
    while (left <= right) {
      pivotIdx = (left + right) / 2;
      pivotElement = matrix[pivotIdx / n][pivotIdx % n];
      if (target == pivotElement)
        return true;
      else {
        if (target < pivotElement)
          right = pivotIdx - 1;
        else
          left = pivotIdx + 1;
      }
    }
    return false;
  }

  /*
  public boolean searchMatrix(int[][] matrix, int target) {
        int row = 0;
        int col = matrix[0].length-1;
        while(row<=matrix.length-1&&col>=0){
            if(matrix[row][col]==target) return true;
            else if(matrix[row][col]>target){
                col--;
            }else{
                row++;
            }
        }
        return false;
    }
   */

}
