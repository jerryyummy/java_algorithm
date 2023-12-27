import java.util.Arrays;

public class Leetcode215 {
    public int findKthLargest(int[] nums, int k) {
        int pivot = nums[0];
        int i = 1, j = nums.length-1;
        while(i<=j){
            while(i<=j && nums[i]>pivot){
                i++;
            }
            while(i<=j && nums[j]<pivot){
                j--;
            }
            if(i>j) break;
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
        nums[0] = nums[j];
        nums[j] = pivot;
        if(j==k-1) return nums[j];
        else if(j<k-1){
            return findKthLargest(Arrays.copyOfRange(nums,j+1,nums.length),k-j-1);
        }else{
            return findKthLargest(Arrays.copyOfRange(nums,0,j),k);
        }
    }
}
