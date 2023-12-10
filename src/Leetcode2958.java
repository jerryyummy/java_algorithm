import java.util.HashMap;
import java.util.HashSet;

public class Leetcode2958 {
    public int maxSubarrayLength(int[] nums, int k) {
        int left = 0, right = 0;
        int length = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        while (right<nums.length){
            map.put(nums[right],map.getOrDefault(nums[right],0)+1);
            if (map.get(nums[right])<=k) {
                length = Math.max(right-left+1,length);
            }else {
                while (left<=right && map.get(nums[right])>k){
                    map.put(nums[left],map.get(nums[left])-1);
                    left++;
                }
            }
            right++;
        }
        return length;
    }
}
