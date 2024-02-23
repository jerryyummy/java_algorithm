public class Leetcode53 {
  public int maxSubArray(int[] nums) {
    int res = Integer.MIN_VALUE;
    int sum = 0;
    for (int num:nums){
      if (sum+num>0){
        sum = Math.max(sum+num,num);
      }else {
        sum = num;
      }
      res = Math.max(res,sum);
    }
    return Math.max(res,sum);
  }

}
