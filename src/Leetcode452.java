import java.util.Arrays;
import java.util.Comparator;

public class Leetcode452 {
  public int findMinArrowShots(int[][] points) {
    int res = 1;
    // Sort by end positions
    Arrays.sort(points, new Comparator<int[]>() {
      @Override
      public int compare(int[] a, int[] b) {
        return Integer.compare(a[1], b[1]);
      }
    });
    int maxRight = points[0][1];
    for (int i = 0; i < points.length; i++) {
      if(points[i][0]<=maxRight) continue;
      else{
        maxRight = points[i][1];
        res++;
      }
    }
    return res;
  }

}
