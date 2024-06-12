package snowflake;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MinimumSegments {
  public static int minimumDivision(List<Integer> a, List<Integer> b, int k) {
    int n = a.size();
    int[][] intervals = new int[n][2];
    for (int i = 0; i < n; i++) {
      intervals[i][0] = a.get(i);
      intervals[i][1] = b.get(i);
    }

    Arrays.sort(intervals, new Comparator<int[]>() {

      @Override
      public int compare(int[] o1, int[] o2) {
        if (o1[0] != o2[0]) {
          return o1[0] - o2[0];
        }else {
          return o1[1] - o2[1];
        }
      }
    });

    int max = intervals[n-1][1];
    int[] dp = new int[max+1];
    for (int i = intervals[0][0]; i <= intervals[0][1]; i++) {
      dp[i] = 1;
    }
    int cur = intervals[0][1];
    for (int i = 1; i < intervals.length; i++) {
      if (intervals[i][0] <= cur) {
        for (int j = cur+1; j <= intervals[i][1] ; j++) {
          dp[j] = dp[cur];
        }
      }else{
        for (int j = cur+1; j <= intervals[i][0]; j++) {
          dp[j] = dp[cur];
        }
        cur = intervals[i][0];
        for (int j = cur+1; j <= intervals[i][1]; j++) {
          dp[j] = dp[cur]+1;
        }
      }
      cur = Math.max(intervals[i][1], cur);
    }

    int res = dp[cur];
    int temp = res;
    for (int i = intervals[0][0]; i <= intervals[n-1][1]-k ; i++) {
      res = Math.min(res, temp-dp[i+k]+dp[i]);
    }

    return res;
  }

  public static void main(String[] args) {
    System.out.println(minimumDivision(Arrays.asList(1,2,5,10), Arrays.asList(2,4,8,11), 2));
  }
}
