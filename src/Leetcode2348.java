public class Leetcode2348 {
    public long zeroFilledSubarray(int[] nums) {
        long sum = 0;
        long count = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == 0){
                count++;
            }else{
                sum += (1+count)*count/2;
                count = 0;
            }
        }
        sum += (1+count)*count/2;
        return sum;
    }
}
