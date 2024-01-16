import java.util.Arrays;

public class Leetcode1498 {
    public int numSubseq(int[] nums, int target) {
        int sum = 0;
        int mod = 1_000_000_007;
        int i = 0, j = nums.length-1;
        Arrays.sort(nums);
        int[] power = new int[nums.length];
        power[0] = 1;
        for (int k = 1; k < nums.length; ++k) {
            power[k] = (power[k - 1] * 2) % mod;
        }

        while (i<=j){
            if (nums[i]+nums[j]>target) j--;
            else{
                sum += power[j-i];
                sum = sum%mod;
                i++;
            }
        }
        return sum;
    }
}
