package snowflake;

import java.util.*;

public class SmallestSet {
  // Sort with respect to end point
  public static int compare(List<Integer> a,
      List<Integer> b)
  {
    if (a.get(1).equals(b.get(1))) {
      return a.get(0).compareTo(b.get(0));
    }
    else {
      return a.get(1).compareTo(b.get(1));
    }
  }

  public static int intersectionSizeTwo(List<List<Integer> > intervals)
  {
    int n = intervals.size();

    // Sort the array
    Collections.sort(intervals, SmallestSet::compare);
    List<Integer> res = new ArrayList<>();

    // Known two rightmost point
    // in the set/res
    res.add(intervals.get(0).get(1) - 1);
    res.add(intervals.get(0).get(1));

    for (int i = 1; i < n; i++) {

      int start = intervals.get(i).get(0);
      int end = intervals.get(i).get(1);

      // Means there is no common between
      // curr interval and intervals
      // before this
      if (start > res.get(res.size() - 1)) {
        res.add(end - 1);
        res.add(end);
      }

      // At least 1 value from current
      // interval matches with previous
      // sets just add 1 max value
      else if (start > res.get(res.size() - 2)) {
        res.add(end);
      }
    }
    return res.size();
  }
}
