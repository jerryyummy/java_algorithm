import java.util.PriorityQueue;
import java.util.Queue;

public class Leetcode1425 {
  public int constrainedSubsetSum(int[] nums, int k) {
    int res = nums[0];
    int[] dp = new int[nums.length];
    dp[0] = nums[0];
    Queue<int[]> queue = new PriorityQueue<>((a,b)->b[0]-a[0]);
    queue.add(new int[]{dp[0], 0});

    for (int i = 1; i < nums.length; i++) {
      while (!queue.isEmpty() && queue.peek()[1] < i-k) {
        queue.poll();
      }

      if (queue.isEmpty()){
        dp[i] = nums[i];
      }else{
        dp[i] = Math.max(0,queue.peek()[0]) + nums[i];
      }
      res = Math.max(res, dp[i]);
      queue.add(new int[]{dp[i],i});
    }

    return res;
  }
}
