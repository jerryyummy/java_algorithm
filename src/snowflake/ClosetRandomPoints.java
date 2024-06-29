package snowflake;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ClosetRandomPoints {
  public static class Point {
    int x, y;

    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  public static long closestSquaredDistance(List<Integer> x, List<Integer> y) {
    int n = x.size();
    Point[] points = new Point[n];

    for (int i = 0; i < n; i++) {
      points[i] = new Point(x.get(i), y.get(i));
    }

    Arrays.sort(points, Comparator.comparingInt(p -> p.x));

    return closestPair(points, 0, n - 1);
  }

  private static long closestPair(Point[] points, int left, int right) {
    if (left >= right) {
      return Long.MAX_VALUE;
    }

    int mid = (left + right) / 2;
    long minDistLeft = closestPair(points, left, mid);
    long minDistRight = closestPair(points, mid + 1, right);
    long minDist = Math.min(minDistLeft, minDistRight);

    return Math.min(minDist, closestCrossPair(points, left, mid, right, minDist));
  }

  private static long closestCrossPair(Point[] points, int left, int mid, int right, long minDist) {
    List<Point> strip = new ArrayList<>();

    for (int i = left; i <= right; i++) {
      if (Math.abs(points[i].x - points[mid].x) < minDist) {
        strip.add(points[i]);
      }
    }

    strip.sort(Comparator.comparingInt(p -> p.y));

    for (int i = 0; i < strip.size(); i++) {
      for (int j = i + 1; j < strip.size() && (strip.get(j).y - strip.get(i).y) * (strip.get(j).y - strip.get(i).y) < minDist; j++) {
        long dx = strip.get(i).x - strip.get(j).x;
        long dy = strip.get(i).y - strip.get(j).y;
        long dist = dx * dx + dy * dy;
        if (dist < minDist) {
          minDist = dist;
        }
      }
    }

    return minDist;
  }
}
