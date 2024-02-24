import java.util.HashMap;

public class Leetcode128 {
  public int longestConsecutive(int[] nums) {
    HashMap<Integer,Integer> map = new HashMap<>();
    for (int num : nums) {
      map.put(num, num);
    }
    int ans = 0;
    for (int num:nums){
      if (map.containsKey(num)){
        int rightMax = map.get(num);
        while (map.containsKey(rightMax+1)){
          int curRight = map.get(rightMax+1);
          map.remove(rightMax+1);
          rightMax = curRight;
        }
        map.put(num,rightMax);
        ans = Math.max(ans,rightMax-num+1);
      }
    }
    return ans;
  }
}
