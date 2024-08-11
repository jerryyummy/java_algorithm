import java.util.*;

public class Main {


//  public static void main(String[] args) {
//    Scanner in = new Scanner(System.in);
//    // 注意 hasNext 和 hasNextLine 的区别
//    int T = in.nextInt();
//    List<Integer> list = new ArrayList<>();
//    for (int i = 0; i < T; i++) {
//      int n = in.nextInt();
//      int k = in.nextInt();
//      int x = in.nextInt();
//      int[] arr = new int[n];
//      for (int j = 0; j < n; j++) {
//        arr[j] = in.nextInt();
//      }
//      list.add(solve(arr, k, x));
//    }
//    StringBuilder stringBuilder = new StringBuilder();
//    for (int i = 0; i < list.size(); i++) {
//      stringBuilder.append(list.get(i));
//      stringBuilder.append(" ");
//    }
//    System.out.println(stringBuilder.toString().trim());
//  }
//
//  public static int solve(int[] array, int k, int x){
//    int length = array.length;
//    int[] dp = new int[length+1];
//    TreeSet<Integer> set = new TreeSet<>();
//    for (int i = 1; i <= length ; i++) {
//      set.add(i);
//    }
//    for (int i = length-1; i >= 0 ; i--) {
//      if (set.contains(array[i])) {
//        set.remove(array[i]);
//      }
//      if (set.isEmpty()) {
//        dp[i] = length+1;
//      }else{
//        dp[i] = set.first();
//      }
//    }
//
//    int res = k*dp[0];
//    for (int i = 1; i <= length; i++) {
//      res = Math.min(res, x*i + k*dp[i]);
//    }
//
//    return res;
//  }

  public static int calcMinimumPower(int[][] grid) {
    int res = Integer.MIN_VALUE;
    Queue<int[]> q = new LinkedList<>();
    int m = grid.length;
    int n = grid[0].length;
    int[][] directions = new int[][]{ {1, 0}, {0, 1}};

    q.add(new int[]{0,0,grid[0][0],grid[0][0]});
    while (!q.isEmpty()) {
      int[] curr = q.poll();
      int row = curr[0];
      int col = curr[1];
      int energy = curr[2];
      int remain = curr[3];
      if (row == m - 1 && col == n - 1) {
        res = Math.max(energy,res);
        continue;
      }
      for (int[] direction : directions) {
        int newRow = row + direction[0];
        int newCol = col + direction[1];
        if (newRow >= 0 && newRow <m && newCol >= 0 && newCol < n ) {
          int temp = Math.min(remain + grid[newRow][newCol], energy);
          q.add(new int[]{newRow,newCol,temp,remain + grid[newRow][newCol]});
        }
      }
    }

    return res==Integer.MIN_VALUE?1:-1*res+1;
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int res;

    int grid_rows = 0;
    int grid_cols = 0;
    grid_rows = in.nextInt();
    grid_cols = in.nextInt();

    int[][] grid = new int[grid_rows][grid_cols];
    for(int grid_i=0; grid_i<grid_rows; grid_i++) {
      for(int grid_j=0; grid_j<grid_cols; grid_j++) {
        grid[grid_i][grid_j] = in.nextInt();
      }
    }

    if(in.hasNextLine()) {
      in.nextLine();
    }


    res = calcMinimumPower(grid);
    System.out.println(String.valueOf(res));
  }
}
