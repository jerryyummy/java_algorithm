class SubTreeInfo{
    int sum;
    boolean isBST;
    int maxleft;
    int minright;

    public SubTreeInfo(int sum, boolean isBST, int maxleft, int minright){
        this.sum = sum;
        this.isBST = isBST;
        this.minright = minright;
        this.maxleft = maxleft;
    }
}

public class MaxSumBST {
    int res = 0;
    public int maxSumBST(TreeNode root) {
        recur(root);
        return res;
    }

    public SubTreeInfo recur(TreeNode root){
        int min = root.val;
        int max = root.val;
        if(root==null) return new SubTreeInfo(0,true, max,min);
        SubTreeInfo left = recur(root.left);
        SubTreeInfo right = recur(root.right);
        if(!left.isBST || !right.isBST || root.val< left.minright || root.val>right.maxleft){
            return new SubTreeInfo(0,false, max, min);
        }else{
            res = Math.max(res, left.sum+ right.sum+root.val);
            min = left.maxleft;
            max = right.minright;
            return new SubTreeInfo(left.sum+ right.sum+root.val, true, max,min);
        }
    }
}
