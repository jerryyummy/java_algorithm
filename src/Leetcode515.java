import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Leetcode515 {
  public List<Integer> largestValues(TreeNode root) {
    List<Integer> list = new ArrayList<>();
    Queue<TreeNode> queue =new ArrayDeque<>();
    if (root!=null) queue.add(root);
    while(queue.size() != 0){
      int size = queue.size();
      int max = Integer.MIN_VALUE;
      for (int i = 0; i < size; i++) {
        TreeNode node = queue.poll();
        max = Math.max(max,node.val);
        if(node.left!=null) queue.add(node.left);
        if(node.right!=null) queue.add(node.right);
      }
      list.add(max);
    }
    return list;
  }
}
