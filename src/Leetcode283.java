public class Leetcode283 {
    public void moveZeroes(int[] nums) {
        int i = 0, j = 0;
        while (j<nums.length){
            while (j<nums.length && nums[j]==0){
                j++;
            }
            while (i<j && nums[i]!=0){
                i++;
            }
            if(j<nums.length && i<j) {
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
            }
            j++;
            i++;
        }
    }
}
