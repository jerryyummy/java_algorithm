import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    int[][] directions = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
    Scanner s = new Scanner(System.in);
    int m = s.nextInt();
    int n = s.nextInt();
    int[][] matrix = new int[m][n];
    for(int i = 0;i<m;i++){
      for(int j = 0;j<n;j++){
        matrix[i][j] = s.nextInt();
      }
    }

    for(int i = 0;i<m;i++){
      for(int j = 0;j<n;j++){
        System.out.println(matrix);
      }
    }

    Queue<int[]> queue = new PriorityQueue<>((a,b)->a[2]-b[2]);
    for(int i = 0;i<m;i++){
      for(int j = 0;j<n;j++){
        if(matrix[i][j] == 0){
          queue.add(new int[]{i,j,0});
        }
      }
    }

    int res = 0;
    while(!queue.isEmpty()){
      int[] point = queue.poll();
      for(int[] direction : directions){
        int x = point[0] + direction[0];
        int y = point[1] + direction[1];
        if(x >= 0 && x < m && y >= 0 && y < n){
          if(matrix[x][y] == 1){
            matrix[x][y] = 0;
            queue.add(new int[]{x,y,point[2]+1});
            res+=point[2];
          }else if(matrix[x][y] == 0){
            queue.add(new int[]{x,y,point[2]+1});
          }
        }
      }
    }

    System.out.println(res);
  }


}

