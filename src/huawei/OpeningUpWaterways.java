package huawei;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class OpeningUpWaterways {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int N = scanner.nextInt();

    int[] x = new int[N];
    int[] y = new int[N];

    for (int i = 0; i < N; i++) {
      x[i] = scanner.nextInt();
    }

    for (int i = 0; i < N; i++) {
      y[i] = scanner.nextInt();
    }

    int count = 0;
    count = calculateMaxNonIntersectingPaths(x,y);

    System.out.println(count);
  }

  private static int calculateMaxNonIntersectingPaths(int[] southCities, int[] northCities) {
    int count = 0;
    List<Line> lines = new ArrayList<>();
    for (int i = 0; i < southCities.length; i++) {
      lines.add(new Line(southCities[i],northCities[i]));
    }

    Collections.sort(lines, new Comparator<Line>() {
      @Override
      public int compare(Line o1, Line o2) {
        return o1.up-o2.up;
      }
    });
    int[] dp = new int[lines.size()+1];
    for (int i = 1; i <= northCities.length; i++) {
      dp[i] = 1;
      for (int j = 1; j < i; j++) {
        if (lines.get(i-1).down > lines.get(j-1).down) {
          dp[i] = Math.max(dp[i], dp[j] + 1);
        }
      }
      count = Math.max(count, dp[i]);
    }

    return count;
  }

  static class Line{
    int up;
    int down;

    public Line(int up, int down) {
      this.up = up;
      this.down = down;
    }
  }
}
