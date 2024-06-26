import java.util.Arrays;
import java.util.HashMap;

public class Leetcode2263 {
  int helper(int[] nums, int[] levels) {
    var dp = new HashMap<Integer,Integer>();
    for(var num : nums) {
      var cur_res = Integer.MAX_VALUE;
      for(var level : levels) {
        var prev_res = dp.getOrDefault(level,0);
        cur_res = Math.min(cur_res, prev_res+Math.abs(num-level));
        dp.put(level,cur_res);
      }
    }
    return dp.get(levels[levels.length-1]);
  }

  public int convertArray(int[] nums) {
    var levels = Arrays.stream(nums).distinct().sorted().toArray();
    var nums2 = new int[nums.length];
    for(int i=0; i<nums.length; i++){
      nums2[nums.length-1-i] = nums[i];
    }
    return Math.min(helper(nums, levels),helper(nums2, levels));
  }

  /*
      int helper(int[] nums, int[] levels) {
        var que = new PriorityQueue<Integer>();
        var res = 0;
        for(var num : nums) {
            if(!que.isEmpty() && num<(-que.peek())) {
                res += (-que.poll())-num;
                que.offer(-num);
            }
            que.offer(-num);
        }
        return res;
    }

    public int convertArray(int[] nums) {
        var levels = Arrays.stream(nums).distinct().sorted().toArray();
        var nums2 = new int[nums.length];
        for(int i=0; i<nums.length; i++){
            nums2[nums.length-1-i]=nums[i];
        }
        return Math.min(helper(nums, levels),helper(nums2, levels));
    }
   */
}
