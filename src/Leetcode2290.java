import java.util.Arrays;
import java.util.PriorityQueue;

public class Leetcode2290 {
  public int minimumObstacles(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;
    PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[0]-b[0]);
    int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};
    pq.offer(new int[]{0,0,0});
    int[][] steps = new int[m][n];
    for(int[] temp:steps)
      Arrays.fill(temp,Integer.MAX_VALUE);
    while(!pq.isEmpty()){
      int[] cur = pq.poll();
      int step = cur[0];
      int x = cur[1];
      int y = cur[2];
      if (x==m-1 && y==n-1){
        return step;
      }
      if(steps[x][y] <= step) continue;
      steps[x][y] = step;
      for(int[] direction : directions){
        int newX = x + direction[0];
        int newY = y + direction[1];
        if (newX >= 0 && newX < m && newY >= 0 && newY < n){
          if (grid[newX][newY] == 1){
            pq.offer(new int[]{step+1, newX, newY});
          }else{
            pq.offer(new int[]{step, newX, newY});
          }
        }
      }
    }
    return -1;
  }
}
