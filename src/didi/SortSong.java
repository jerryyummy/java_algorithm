package didi;

import java.util.Scanner;
import java.util.Arrays;

public class SortSong {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int t = scanner.nextInt(); // 读取测试用例数量
    for (int i = 0; i < t; i++) {
      int n = scanner.nextInt(); // 每组数据中的元素数量
      int[][] arr = new int[n][2]; // 存储每个元素及其操作

      // 读取每组数据
      for (int j = 0; j < n; j++) {
        arr[j][0] = scanner.nextInt(); // 值
        arr[j][1] = scanner.nextInt(); // 位置
      }
      solve(arr);
    }
    scanner.close();
  }

  public static void solve(int[][] arr) {
    Arrays.sort(arr, (a, b)->a[1]-b[1]);
    boolean can = true;
    int state = 0; // -1是连续回收状态
    // 对于 1100-10-10-1类的阵型来说
    // 从消耗-1的角度，最少只需要一个1就可以消耗所有的-1
    // 最多可以消耗两个1
    // 也就是说，只要在出现1的位置后出现的-1都可以被清除
    // 反之，若干个1后面只要出现一个-1就可以成立
    // 因此，对于每一段连续的10组合和连续的-10组合进行统计刷新即可
    // 进而发现0不影响状态改变，则只考虑1和-1
    for(int[] x:arr){
      if(x[0]==1){
        if(state==0) state = 1;
        else if(state==-1){
          state = 1;
        }
      }else if(x[0]==-1){
        if(state==0){
          can = false;
          break;
        }
        else if(state==1){
          state = -1;
        }
      }

    }
    if(state==1) can = false;
    System.out.println(can?"YES":"NO");
  }
}
