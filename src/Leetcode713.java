public class Leetcode713 {
  public int numSubarrayProductLessThanK(int[] nums, int k) {
    int i = 0,j = 0;
    int cur = 1;
    int res = 0;
    while (j<nums.length) {
      cur *= nums[j];

      while(i<=j && cur>=k){
        cur /= nums[i];
        i++;
      }

      res += (j-i+1);

      j++;
    }

    return res;
  }
}
