import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Leetcode173 {
  class BSTIterator {
    List<Integer> res = new ArrayList<>();
    int index = 0;

    public BSTIterator(TreeNode root) {
      Deque<TreeNode>stack = new ArrayDeque<>();
      TreeNode current = root;

      while (current != null || !stack.isEmpty()) {
        while (current != null) {
          stack.push(current);
          current = current.left;
        }
        current = stack.pop();
        res.add(current.val);
        current = current.right;
      }

    }

    public int next() {
      return res.get(index++);
    }

    public boolean hasNext() {
      return index==res.size();
    }
  }
}
