public class Leetcode275 {
  public int hIndex(int[] citations) {
    int n = citations.length;
    int low = 0, high = n - 1;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (citations[mid] == n - mid) {
        return citations[mid];
      } else if (citations[mid] < n - mid) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    // If we exit the loop, it means we didn't find an exact match for citations[mid] == n - mid.
    // However, the h-index is n - low, as low is the point where citations[low] >= n - low holds true.
    return n - low;
  }

  public static void main(String[] args) {
    Leetcode275 solution = new Leetcode275();
    solution.hIndex(new int[]{0,1,3,5,6});
  }
}
