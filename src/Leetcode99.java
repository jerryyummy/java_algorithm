import java.util.Stack;

public class Leetcode99 {
    public void swap(TreeNode a, TreeNode b) {
        int tmp = a.val;
        a.val = b.val;
        b.val = tmp;
    }

    public void recoverTree(TreeNode root) {
        TreeNode x = null;
        TreeNode y = null;
        TreeNode pre = null;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (pre != null && pre.val > root.val) {
                y = root;
                if (x == null) {
                    x = pre;
                }else {
                    break;
                }
            }
            pre = root;
            root = root.right;
        }
        swap(x, y);
    }
}
