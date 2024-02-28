import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Leetcode106 {
//    public TreeNode buildTree(int[] inorder, int[] postorder) {
//        if(postorder.length==0) return null;
//        int index = 0;
//        for(int i=0;i<inorder.length;i++){
//            if(inorder[i]==postorder[postorder.length-1]){
//                index = i;
//                break;
//            }
//        }
//        TreeNode root = new TreeNode(postorder[postorder.length-1]);
//        root.left = buildTree(Arrays.copyOfRange(inorder,0,index),Arrays.copyOfRange(postorder,0,index));
//        root.right = buildTree(Arrays.copyOfRange(inorder,index+1,inorder.length),Arrays.copyOfRange(postorder,index,postorder.length-1));
//        return root;
//    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (postorder == null || postorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[postorder.length - 1]);
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        stack.push(root);
        int inorderIndex = inorder.length - 1;
        for (int i = postorder.length - 2; i >= 0; i--) {
            int postorderVal = postorder[i];
            TreeNode node = stack.peek();//获得目前子树的root
            if (node.val != inorder[inorderIndex]) {
                node.right = new TreeNode(postorderVal);
                stack.push(node.right);
            } else {//遍历到root
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex--;
                }
                node.left = new TreeNode(postorderVal);
                stack.push(node.left);
            }
        }
        return root;
    }

}
