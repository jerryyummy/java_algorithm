public class Leetcode33 {
  public int search(int[] nums, int target) {
    int len = nums.length;
    int left = 0, right = len-1;
    while(left <= right){
      int mid = (left + right) / 2;
      if(nums[mid] == target)
        return mid;
      else if(nums[mid] < nums[right]){//mid到right是递增
        if(nums[mid] < target && target <= nums[right])//结果在递增的部分里面
          left = mid+1;
        else
          right = mid-1;
      }
      else{
        if(nums[left] <= target && target < nums[mid])//结果在左边的递增部分里面
          right = mid-1;
        else
          left = mid+1;
      }
    }
    return -1;
  }
}
