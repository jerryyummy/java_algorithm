import java.util.*;

/*
Given the root of a binary tree and an array of TreeNode objects nodes, return the lowest common ancestor (LCA) of all the nodes in nodes.
All the nodes will exist in the tree, and all values of the tree's nodes are unique.

Extending the definition of LCA on Wikipedia: "The lowest common ancestor of n nodes p1, p2, ..., pn in a binary tree T is the lowest node that
has every pi as a descendant (where we allow a node to be a descendant of itself) for every valid i". A descendant of a node x is a node y that
is on the path from node x to some leaf node.
 */
public class Leetcode1676 {
    Set<Integer> set = new HashSet<>();
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        for (TreeNode node: nodes){
            set.add(node.val);
        }
        return recur(root);
    }

    public TreeNode recur(TreeNode root){
        if (root==null||set.contains(root.val)) return root;
        TreeNode left = recur(root.left),right = recur(root.right);
        if (left!=null&&right!=null) return root;
        else if (left==null) return right;
        else return left;
    }
}
