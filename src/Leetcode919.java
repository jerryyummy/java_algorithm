import java.util.LinkedList;
import java.util.Queue;

class CBTInserter {
  TreeNode root;

  public CBTInserter(TreeNode root) {
    this.root = root;
  }

  public int insert(int val) {
    Queue<TreeNode> queue = new LinkedList<>();
    if (root==null) return -1;
    queue.add(root);
    while (!queue.isEmpty()){
      TreeNode node = queue.remove();
      if (node.left==null){
        node.left = new TreeNode(val);
        return node.val;
      }else if (node.right==null){
        node.right = new TreeNode(val);
        return node.val;
      }else{
        queue.add(node.left);
        queue.add(node.right);
      }
    }
    return -1;
  }

  public TreeNode get_root() {
    return root;
  }
}
