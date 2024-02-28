import java.util.Arrays;

public class Leetcode105 {
  public TreeNode buildTree(int[] preorder, int[] inorder) {
    if(preorder.length==0) return null;
    int index = 0;
    for (int i = 0; i < inorder.length; i++) {
      if(inorder[i]==preorder[0]){
        index = i;
        break;
      }
    }
    TreeNode node = new TreeNode(preorder[0]);
    node.left = buildTree(Arrays.copyOfRange(preorder,1,1+index),Arrays.copyOfRange(inorder,0,index));
    node.right = buildTree(Arrays.copyOfRange(preorder,1+index,preorder.length),Arrays.copyOfRange(inorder,index+1,inorder.length));
    return node;
  }
}
