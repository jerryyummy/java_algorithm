import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    // 示例代码没有处理多个测试用例的循环
    int n = in.nextInt();
    int m = in.nextInt();
    int r = in.nextInt();
    int[] array1 = new int[n];
    for (int i = 0; i < n; i++) {
      array1[i] = in.nextInt();
    }
    boolean[][] matrix = new boolean[n][n];
    for (int i = 0; i < m; i++) {
      int node1 = in.nextInt();
      int node2 = in.nextInt();
      matrix[node1-1][node2-1] = true;
      matrix[node2-1][node1-1] = true;
    }
    List<List<Integer>> adj = new ArrayList<>();
    for (int j = 0; j < n; j++) {
      List<Integer> temp = new ArrayList<>();
      for (int k = 0; k < n; k++) {
        if (matrix[j][k]) {
          temp.add(k);
        }
      }
      adj.add(temp);
    }
    int q = in.nextInt();
    for (int i = 0; i < q; i++) {
      int k = in.nextInt();
      int x = in.nextInt();
      System.out.println(solve(array1, adj, r, k, x));
    }
    in.close(); // 关闭Scanner
  }

  public static int solve(int[] array, List<List<Integer>> adj, int center, int k, int x){
      Queue<String[]> queue = new LinkedList<>();
      String temp = "";
      queue.add(new String[]{String.valueOf(x-1),""+(x-1)});
      boolean[] visit = new boolean[array.length];
      visit[x-1] = true;
      while (!queue.isEmpty()){
        int node  = Integer.parseInt(queue.element()[0]);
        String path = queue.remove()[1];
        if (node == center-1){
          temp = path;
          break;
        }
        for(int next: adj.get(node)){
          if(!visit[next]){
            visit[next] = true;
            queue.add(new String[]{String.valueOf(next), path+","+next});
          }
        }
      }
      String[] nodes = temp.split(",");
      int res = 0;
    for (int i = 0; i < nodes.length; i++) {
      if (i%k==0){
        int node = Integer.parseInt(nodes[i]);
        res+=array[node];
      }
    }
    return res;
  }

  public static int[] compute(int[] array1, int[] array2, int t){
    Arrays.sort(array1);
    Arrays.sort(array2);
    int a = 0, b = 0;
    int sum1 = 0;
    for(int num:array1){
      if(sum1+num<=t){
        sum1+=num;
        a++;
      }else{
        break;
      }
    }

    int sum2 = 0;
    for(int num:array2){
      if(sum2+num<=t){
        sum2+=num;
        b++;
      }else{
        break;
      }
    }

    if(a>=b){
      return new int[]{a,1};
    }else{
      return new int[]{b,3};
    }
  }
}
