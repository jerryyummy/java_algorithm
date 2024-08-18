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

}
