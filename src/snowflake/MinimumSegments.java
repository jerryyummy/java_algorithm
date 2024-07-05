package snowflake;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MinimumSegments {
  static class IntervalComparator implements Comparator<int[]> {
    @Override
    public int compare(int[] a, int[] b) {
      return (a[0] != b[0]) ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1]);
    }
  }

  public static int minimumDivision(List<Integer> a, List<Integer> b, int k) {
    // Write your code here
    int n = a.size();
    List<int[]> intervals = new ArrayList<>();

    // 合并 a 和 b 列表到 intervals 列表中
    for (int i = 0; i < n; i++) {
      intervals.add(new int[]{a.get(i), b.get(i)});
    }

    intervals.sort(new IntervalComparator()); // sort based on start - O(NlogN)

    // merge intervals - O(N)
    List<int[]> mergedIntervals = new ArrayList<>();
    int[] cur = intervals.get(0);
    for (int i = 1; i < n; i++) {
      if (intervals.get(i)[0] <= cur[1]) {
        cur[1] = Math.max(cur[1], intervals.get(i)[1]);
      } else {
        mergedIntervals.add(cur);
        cur = intervals.get(i);
      }
    }
    mergedIntervals.add(cur);

    n = mergedIntervals.size(); // we have n distinct intervals now

    // For each interval, check how many can it cover with extra k - O(N)
    // 2 pointer approach
    int i = 0, j = 0;
    int ans = n;
    while (j < n) {
      if (mergedIntervals.get(j)[0] <= mergedIntervals.get(i)[1] + k) {
        j++; // can be combined, move to next
      } else {
        // i to j-1 can be combined together => number of intervals = n - (j - i)
        ans = Math.min(ans, n - (j - i - 1));
        i++;
      }
    }
    ans = Math.min(ans, n - (j - i - 1));
    return ans;
  }

  public static void main(String[] args) {
    System.out.println(minimumDivision(Arrays.asList(1,2,5,10), Arrays.asList(2,4,8,11), 2));
  }
}
