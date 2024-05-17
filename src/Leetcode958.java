import java.util.LinkedList;

public class Leetcode958 {
  /**
   * 层序遍历二叉树，当出现 null 值时停止遍历，如果此时还有没有遍历到的结点，说明该树非完全二叉树。
   */
  public boolean isCompleteTree(TreeNode root) {
    LinkedList<TreeNode> q = new LinkedList<>();
    q.addLast(root);
    TreeNode cur;
    while ((cur = q.removeFirst()) != null) {
      q.addLast(cur.left);
      q.addLast(cur.right);
    }
    while (!q.isEmpty()) {
      if (q.removeLast() != null) {
        return false;
      }
    }
    return true;
  }

  /*
  * 使用递归
   */
//  int n = 0, p = 0;
//  public boolean isCompleteTree(TreeNode root) {
//    if(!dfs(root,1)) return false;
//    return n == p;
//  }
//  public boolean dfs(TreeNode root , int k) //k是当前节点编号
//  {
//    if(root == null) return true;  //递归到了叶子节点
//    if(k > 100) return false;
//    n++;  p = Math.max(p,k); //记录节点数和最大节点编号值
//    return dfs(root.left,2*k)&&dfs(root.right,2*k + 1); //递归左右子树
//  }

}
