import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Leetcode103 {
  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    if (root==null) return res;
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    int level = 1;
    while(!queue.isEmpty()){
      List<Integer> temp = new ArrayList<>();
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        TreeNode node = queue.remove();
        temp.add(node.val);
        if (node.left!=null) queue.add(node.left);
        if (node.right!=null) queue.add(node.right);
      }
      if (level%2==1) res.add(temp);
      else {
        Collections.reverse(temp);
        res.add(temp);
      }
      level++;
    }
    return res;
  }
}
