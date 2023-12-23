public class Leetcode124 {
    int res = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        recur(root);
        return res;
    }

    public int recur(TreeNode root){
        if(root==null) return 0;
        int left = Math.max(recur(root.left), 0);
        int right = Math.max(recur(root.right), 0);
        res = Math.max(res, left+right+root.val);
        return Math.max(left,right)+root.val;
    }

}
