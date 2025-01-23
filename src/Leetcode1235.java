import java.util.Arrays;

public class Leetcode1235 {
  class Job{
    int start;
    int end;
    int profit;
    public Job(int start, int end, int profit) {
      this.start = start;
      this.end = end;
      this.profit = profit;
    }
  }

  public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
    Job[] jobs = new Job[startTime.length];
    for (int i = 0; i < startTime.length; i++) {
      jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
    }
    Arrays.sort(jobs, (a,b) -> a.start - b.start);
    int[] dp = new int[jobs.length];
    dp[jobs.length - 1] = jobs[jobs.length - 1].profit;

    for (int i = jobs.length - 2; i >= 0; i--) {
      if(jobs[i].end <= jobs[i + 1].start){
        dp[i] = jobs[i].profit + dp[i + 1];
      }else{
        int index = find(jobs, i);
        if(index == -1){
          dp[i] = Math.max(dp[i+1], jobs[i].profit);
        }else{
          dp[i] = Math.max(dp[i+1], jobs[i].profit + dp[index]);
        }

      }
    }
    return dp[0];
  }

  public int find(Job[] jobs, int start) {
    int left = start+1, right = jobs.length-1;
    int res = -1;
    while(left <= right){
      int mid = (left + right)/2;
      if(jobs[mid].start >= jobs[start].end){
        right = mid-1;
        res = mid;
      }else{
        left = mid + 1;
      }
    }
    return res;
  }
}
