package adobe;

public class MinProblemsSolver {

  public static int minimumProblems(int[] points, int threshold) {
    int n = points.length;
    int target = points[0] + threshold;

    // Check if the difference between the last and first points is smaller than the threshold
    if (points[n - 1] < threshold) return n;

    // Use binary search to find the minimum index that satisfies the threshold
    int j = findMinIndex(points, target);

    // Return the number of problems to be solved
    return ((j+1)/2) +1;
  }

  private static int findMinIndex(int[] points, int target) {
    int left = 0, right = points.length - 1;

    while (left <= right) {
      int mid = left + (right - left) / 2;

      if (points[mid] >= target) {
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }

    return left;
  }


  public static void main(String[] args) {
    int[] points = {1,2,3};
    int threshold = 2;
    System.out.println(minimumProblems(points, threshold));  // 输出应该为3
  }
}
