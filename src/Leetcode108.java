public class Leetcode108 {
  public TreeNode sortedArrayToBST(int[] nums) {
    return merge(nums,0,nums.length-1);
  }

  public TreeNode merge(int[] nums, int left, int right){
    if (left>right) return null;
    int mid = (left+right)/2;
    TreeNode root = new TreeNode(nums[mid]);
    root.left = merge(nums, left, mid-1);
    root.right = merge(nums,mid+1,right);
    return root;
  }
}
