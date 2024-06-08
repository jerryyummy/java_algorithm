package huawei;

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

    return count;
  }
}
