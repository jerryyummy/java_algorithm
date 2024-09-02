import java.util.LinkedList;
import java.util.Queue;

public class Leetcode1609 {
  public boolean isEvenOddTree(TreeNode root) {
    Queue<TreeNode> queue = new LinkedList<>();
    int level = 0;
    queue.add(root);

    while(!queue.isEmpty()){
      int size = queue.size();
      if (level % 2 == 0){
        int low = 0;
        for (int i = 0; i < size; i++) {
          TreeNode node = queue.poll();
          if(node.val <= low || node.val %2 ==0) return false;
          if(node.left != null) queue.add(node.left);
          if(node.right != null) queue.add(node.right);
          low = node.val;
        }
      }else{
        int high = Integer.MAX_VALUE;
        for (int i = 0; i < size; i++) {
          TreeNode node = queue.poll();
          if (node.val >= high || node.val %2 ==1) return false;
          if(node.left != null) queue.add(node.left);
          if(node.right != null) queue.add(node.right);
          high = node.val;
        }
      }
      level++;
    }
    return true;
  }
}
