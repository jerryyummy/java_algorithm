public class Leetcode222 {
//  public int countNodes(TreeNode root) {
//    if(root==null) return 0;
//    int left_depth = 0, right_depth = 0;
//    TreeNode node = root;
//    while(node.left!=null){
//      left_depth++;
//      node = node.left;
//    }
//    node = root;
//    while(node.right!=null){
//      right_depth++;
//      node = node.right;
//    }
//    if(left_depth==right_depth){
//      return (int)Math.pow(2,left_depth+1)-1;
//    }else{
//      return countNodes(root.left)+countNodes(root.right)+1;
//    }
//  }

  public int countNodes(TreeNode root) {
    if(root == null){
      return 0;
    }
    int left = countLevel(root.left);
    int right = countLevel(root.right);
    if(left == right){
      return countNodes(root.right) + (1<<left);
    }else{
      return countNodes(root.left) + (1<<right);
    }
  }
  private int countLevel(TreeNode root){
    int level = 0;
    while(root != null){
      level++;
      root = root.left;
    }
    return level;
  }

}
