import java.util.Arrays;

public class Leetcode238 {
  public int[] productExceptSelf(int[] nums) {
    int n = nums.length;
    int pre = 1, suf = 1;
    int[] ans = new int[n];
    for (int i = 0; i < n; i++) {
      ans[i] = pre;
      pre *= nums[i];
    }
    for (int j = n - 1; j >= 0; j--) {
      ans[j] *= suf;
      suf *= nums[j];
    }
    return ans;
  }

}
