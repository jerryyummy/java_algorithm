public class Leetcode1367 {
    public boolean isSubPath(ListNode head, TreeNode root) {
        if (head == null) return true; // 链表遍历完，匹配成功
        if (root == null) return false; // 树遍历完但链表未遍历完，失败

        // 从当前节点开始匹配，或者从左子树或右子树开始匹配
        return dfs(head, root) || isSubPath(head, root.left) || isSubPath(head, root.right);
    }

    private boolean dfs(ListNode head, TreeNode root) {
        if (head == null) return true; // 链表遍历完，匹配成功
        if (root == null) return false; // 树节点为空，匹配失败

        // 当前节点值不匹配，直接失败
        if (root.val != head.val) return false;

        // 当前节点值匹配，继续匹配链表的下一个节点和树的左右子树
        return dfs(head.next, root.left) || dfs(head.next, root.right);
    }
}
