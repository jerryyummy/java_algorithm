import java.util.ArrayDeque;
import java.util.Deque;

public class Leetcode230 {
  public int kthSmallest(TreeNode root, int k) {
    Deque<TreeNode> stack = new ArrayDeque<>();
    int count = 0;
    while(root!=null){
      stack.push(root);
      root = root.left;
    }
    while(!stack.isEmpty()){
      TreeNode node = stack.pop();
      count++;
      if(count==k){
        return node.val;
      }
      if(node.right!=null){
        stack.push(node.right);
        node = node.right;
        while(node.left!=null){
          stack.push(node.left);
          node = node.left;
        }
      }
    }
    return 0;
  }

  /*
class MyBst {
    TreeNode root;
    Map<TreeNode, Integer> nodeNum;

    public MyBst(TreeNode root) {
        this.root = root;
        this.nodeNum = new HashMap<TreeNode, Integer>();
        countNodeNum(root);
    }

    // 返回二叉搜索树中第k小的元素
    public int kthSmallest(int k) {
        TreeNode node = root;
        while (node != null) {
            int left = getNodeNum(node.left);
            if (left < k - 1) {
                node = node.right;
                k -= left + 1;
            } else if (left == k - 1) {
                break;
            } else {
                node = node.left;
            }
        }
        return node.val;
    }

    // 统计以node为根结点的子树的结点数
    private int countNodeNum(TreeNode node) {
        if (node == null) {
            return 0;
        }
        nodeNum.put(node, 1 + countNodeNum(node.left) + countNodeNum(node.right));
        return nodeNum.get(node);
    }

    // 获取以node为根结点的子树的结点数
    private int getNodeNum(TreeNode node) {
        return nodeNum.getOrDefault(node, 0);
    }
}
   */
}
