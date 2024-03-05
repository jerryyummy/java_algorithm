public class Leetcode153 {
  public int findMin(int[] nums) {
    int left = 0, right = nums.length-1;
    while (left<right){
       int mid = (left+right)/2;
       if (nums[left]<nums[right]){
         right--;
       }else{
         left++;
       }
    }
    return left;
  }
}
