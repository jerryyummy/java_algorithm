import java.util.Arrays;

public class Leetcode106 {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if(postorder.length==0) return null;
        int index = 0;
        for(int i=0;i<inorder.length;i++){
            if(inorder[i]==postorder[postorder.length-1]){
                index = i;
                break;
            }
        }
        TreeNode root = new TreeNode(postorder[postorder.length-1]);
        root.left = buildTree(Arrays.copyOfRange(inorder,0,index),Arrays.copyOfRange(postorder,0,index));
        root.right = buildTree(Arrays.copyOfRange(inorder,index+1,inorder.length),Arrays.copyOfRange(postorder,index,postorder.length-1));
        return root;
    }
}
