import java.util.Stack;

public class Leetcode2640 {
  public long[] findPrefixScore(int[] nums) {
    long[] arr= new long[nums.length];
    long max=nums[0];
    long sum=0;
    for(int i=0;i<nums.length;i++){
      max=max<=nums[i]?nums[i]:max;
      sum=sum+nums[i]+max;
      arr[i]=sum;
    }
    return arr;
  }
}
