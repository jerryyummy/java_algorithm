package mayi;

import java.util.Scanner;

public class ShapeIdentifier {
  public static int[][] directions = new int[][]{{-1,0},{0,1},{0,-1},{1,0}};

  public static void identifyShape(char[][] matrix, int n, int m) {
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (matrix[i][j] == '*' && check(matrix, n, m, i, j)>=3){
          System.out.println("T");
          return;
        }
      }
    }
    System.out.println("L");
  }

  public static int check(char[][] matrix, int n, int m, int row, int col){
    int count = 0;
    for(int[] direction:directions){
      int x = row+direction[0];
      int y = col+direction[1];
      if (x>=0 && x<n && y>=0 && y<m){
        if (matrix[x][y] == '*') count++;
      }
    }
    return count;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int m = scanner.nextInt();
    char[][] matrix = new char[n][m];
    for (int i = 0; i < n; i++) {
      matrix[i] = scanner.next().toCharArray();
    }
    identifyShape(matrix, n, m);
  }
}