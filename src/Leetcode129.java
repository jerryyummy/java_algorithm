public class Leetcode129 {
  int sum = 0;
  public int sumNumbers(TreeNode root) {
    recur(root,0);
    return sum;
  }

  public void recur(TreeNode node, int cur){
    if(node==null) return;
    if(node.left == null && node.right == null){
      sum+=(cur*10+node.val);
      return;
    }else{
      recur(node.left,cur*10+node.val);
      recur(node.right, cur*10+node.val);
    }
  }
}
