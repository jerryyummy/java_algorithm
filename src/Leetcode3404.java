import java.util.HashMap;
import java.util.Map;

//From A[p] * A[r] == A[q] * A[s]
//we can have A[p] / A[q] == A[s] / A[r]
//cnt will count the frequency of A[p] / A[q].
//
//Enumerate indice r,
//update the cnt first,
//for each s from r + 2 to n - 1,
//res += cnt[A[s] / A[r]].
//
//Return res in the end
public class Leetcode3404 {
  public long numberOfSubsequences(int[] nums) {
    int n = nums.length;
    long res = 0;
    Map<Double, Integer> cnt = new HashMap<>();
    for (int r = 3; r < n - 2; ++r) {
      int q = r - 2;
      for (int p = 0; p < q - 1; ++p) {
        double key = 1.0 * nums[p] / nums[q];
        cnt.put(key, cnt.getOrDefault(key, 0) + 1);
      }
      for (int s = r + 2; s < n; ++s) {
        double key = 1.0 * nums[s] / nums[r];
        res += cnt.getOrDefault(key, 0);
      }
    }
    return res;
  }
}
