public class Leetcode1343 {
  public int numOfSubarrays(int[] arr, int k, int threshold) {
    int sum = 0;
    for (int i = 0; i < k; i++) {
      sum += arr[i];
    }
    int res = 0;
    if (sum >= threshold * k) {
      res = 1;
    }
    for (int i = k; i < arr.length; i++) {
      sum += arr[i];
      sum -= arr[i - k];
      if (sum >= threshold * k) {
        res += 1;
      }
    }
    return res;
  }
}
