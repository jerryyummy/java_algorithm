public class Leetcode530 {
  private Integer prev = null;
  private int minDiff = Integer.MAX_VALUE;

  public int getMinimumDifference(TreeNode root) {
    inOrder(root);
    return minDiff;
  }

  private void inOrder(TreeNode root) {
    if (root == null) return;

    // Traverse the left subtree
    inOrder(root.left);
    // Process the current node
    if (prev != null) {
      // Update the minimum difference
      minDiff = Math.min(minDiff, root.val - prev);
    }
    prev = root.val; // Update prev to the current node value

    // Traverse the right subtree
    inOrder(root.right);
  }
}
