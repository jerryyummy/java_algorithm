import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leetcode56 {
  public int[][] merge(int[][] intervals) {
    Arrays.sort(intervals,(a,b)->a[0]-b[0]);
    List<int[]> res = new ArrayList<>();
    int i = 0;
    while (i<intervals.length){
      int low = intervals[i][0];
      int high = intervals[i][1];
      i++;
      while (i<intervals.length && high>=intervals[i][0]){
        high = Math.max(high,intervals[i][1]);
        i++;
      }
      res.add(new int[]{low,high});
    }
    return res.toArray(new int[0][2]);
  }
}
