import java.util.Arrays;

public class Leetcode1838 {
    public int maxFrequency(int[] nums, int k) {
        if (nums.length==1) return 1;
        Arrays.sort(nums);
        int i = 0, j = 1;
        long prefix = nums[0];
        int res = 1;
        while (j<nums.length){
            prefix+=nums[j];
            if ((long) nums[j] *(j-i+1)-prefix<=k){
                res = Math.max(res,j-i+1);
            }else{
                prefix-=nums[i];
                i++;
            }
            j++;
        }
        return res;
    }
}
