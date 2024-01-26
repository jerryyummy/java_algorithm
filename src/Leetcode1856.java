import java.util.Stack;

public class Leetcode1856 {
  public int maxSumMinProduct(int[] nums) {
    if(nums.length==0){
      return 0;
    }
    Stack<long[]> minStack = new Stack<>();
    int n = nums.length, M = (int) (1e9+7);
    long max =0;
    long prefixSum=0;
    for (int i = 0; i < nums.length; i++) {
      while (!minStack.isEmpty() && minStack.peek()[0]> nums[i]){
        long min = minStack.pop()[0];//得到目前元素之前的最小值
        long leftPrefix = minStack.isEmpty() ? 0 : minStack.peek()[1];//得到包含上一个最小元素的前缀和
        long rightPrefix = prefixSum;//得到目前的前缀和
        long product = min*(rightPrefix-leftPrefix);
        max = Math.max(max,product);
      }
      prefixSum+=nums[i];
      minStack.push(new long[]{nums[i],prefixSum});
    }

    //处理剩下的元素
    while (!minStack.isEmpty()){
      long min = minStack.pop()[0];
      long leftPrefix = minStack.isEmpty() ? 0 : minStack.peek()[1];
      long rightPrefix = prefixSum;//得到目前的前缀和
      long product = min*(rightPrefix-leftPrefix);
      max = Math.max(max,product);
    }

    return (int) (max%M);
  }
}
