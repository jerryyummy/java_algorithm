public class Leetcode333 {
  // Track previous node while doing inorder traversal.
  private TreeNode previous;

  // Function to check if given tree is a valid Binary Search Tree or not.
  private boolean isValidBST(TreeNode root) {
    if (root == null) {
      return true;
    }

    if(!isValidBST(root.left)) {
      return false;
    }

    if (previous != null && previous.val >= root.val) {
      return false;
    }

    previous = root;

    return isValidBST(root.right);
  }

  private int countNodes(TreeNode root) {
    if (root == null) {
      return 0;
    }
    return 1 + countNodes(root.left) + countNodes(root.right);
  }

  public int largestBSTSubtree(TreeNode root) {
    if (root == null) {
      return 0;
    }

    previous = null;

    if (isValidBST(root)) {
      return countNodes(root);
    }

    return Math.max(largestBSTSubtree(root.left), largestBSTSubtree(root.right));
  }

}
