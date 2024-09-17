import java.util.Stack;

public class Leetcode426 {
  public TreeNode treeToDoublyList(TreeNode root) {
    if (root == null) return null;

    Stack<TreeNode> stack = new Stack<>();
    TreeNode head = null;
    TreeNode prev = null;

    // Initialize stack with left-most nodes
    while (root != null) {
      stack.push(root);
      root = root.left;
    }

    // Process nodes in order
    while (!stack.isEmpty()) {
      TreeNode node = stack.pop();
      if (prev == null) {
        head = node; // Initialize head on first iteration
      } else {
        prev.right = node;
        node.left = prev;
      }
      prev = node;

      // Push right child if exists
      if (node.right != null) {
        TreeNode rightChild = node.right;
        while (rightChild != null) {
          stack.push(rightChild);
          rightChild = rightChild.left;
        }
      }
    }

    // Connect the last node back to the head to form a circular list
    prev.right = head;
    head.left = prev;

    return head;
  }
}
