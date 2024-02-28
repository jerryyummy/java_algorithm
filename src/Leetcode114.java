public class Leetcode114 {
  public void flatten(TreeNode root) {
    flattenTree(root);
  }

  private TreeNode flattenTree(TreeNode node) {
    if (node == null) return null;

    // If the node is a leaf, return it
    if (node.left == null && node.right == null) {
      return node;
    }

    // Flatten the left and right subtree
    TreeNode leftTail = flattenTree(node.left);
    TreeNode rightTail = flattenTree(node.right);

    // If there's a left subtree, we insert it between the node and the right subtree
    if (leftTail != null) {
      leftTail.right = node.right;
      node.right = node.left;
      node.left = null; // Set the left child to null
    }

    // We need to return the rightmost node after the flattening
    return rightTail != null ? rightTail : leftTail;
  }

  /*
  public void flatten(TreeNode root) {
    while (root != null) {
        //左子树为 null，直接考虑下一个节点
        if (root.left == null) {
            root = root.right;
        } else {
            // 找左子树最右边的节点
            TreeNode pre = root.left;
            while (pre.right != null) {
                pre = pre.right;
            }
            //将原来的右子树接到左子树的最右边节点
            pre.right = root.right;
            // 将左子树插入到右子树的地方
            root.right = root.left;
            root.left = null;
            // 考虑下一个节点
            root = root.right;
        }
    }
  }
   */
}
