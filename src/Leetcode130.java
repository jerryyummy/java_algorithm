import java.util.LinkedList;
import java.util.Queue;

public class Leetcode130 {
  public void solve(char[][] board) {
    Queue<int[]> queue = new LinkedList<>();
    int m = board.length, n = board[0].length;
    for (int i = 0; i < m; i++) {
      if(board[i][0]=='O') queue.add(new int[]{i,0});
      if(board[i][n-1]=='O') queue.add(new int[]{i,n-1});
    }
    for (int i = 1; i < n-1; i++) {
      if(board[0][i]=='O') queue.add(new int[]{0,i});
      if (board[m-1][i]=='O') queue.add(new int[]{m-1,i});
    }

    int[][] directions = new int[][]{{0,-1},{0,1},{-1,0},{1,0}};
    boolean[][] visited = new boolean[m][n];
    while (!queue.isEmpty()){
      int[] position = queue.remove();
      int x = position[0];
      int y = position[1];
      board[x][y] = 'E';
      for(int[] direction:directions){
        int newX = x+direction[0];
        int newY = y+direction[1];
        if(newX>=0 && newX<m && newY>=0 && newY<n){
          if (board[newX][newY]=='O' && !visited[newX][newY]){
            visited[newX][newY] = true;
            queue.add(new int[]{newX, newY});
          }
        }
      }
    }

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (board[i][j] == 'O') board[i][j] = 'X';
        if (board[i][j] == 'E') board[i][j] = 'O';
      }
    }
  }
}
