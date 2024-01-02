import java.util.LinkedList;
import java.util.Queue;

public class Leetcode2265 {
    public int averageOfSubtree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int res = 0;
        while (!queue.isEmpty()){
            TreeNode node = queue.remove();
            int[] temp = dfs(node);
            if (node.val == temp[0]/temp[1]) res++;
            if (node.left!=null) queue.add(node.left);
            if (node.right!=null) queue.add(node.right);
        }
        return res;
    }

    public int[] dfs(TreeNode root){
        if (root==null) return new int[2];
        int[] left = dfs(root.left);
        int[] right = dfs(root.right);
        return new int[]{left[0]+right[0]+root.val,left[1]+right[1]+1};
    }
}
