import java.util.ArrayList;
import java.util.List;

public class Leetcode863 {
  List<Integer> result = new ArrayList<>();

  public List<Integer> distanceK(TreeNode root, TreeNode target, int k){
    if (root == null || target == null || k < 0) return result;
    dfs(root, target, k);
    return result;
  }

  public int dfs(TreeNode root, TreeNode target, int k){
    if (root == null) return -1;

    if(root.val == target.val){
      findDownwardNodes(root, k);
      return 0;
    }

    int leftDistance = dfs(root.left, target, k);
    if (leftDistance != -1) {
      if (leftDistance == k-1){
        result.add(root.val);
      }else{
        findDownwardNodes(root.right, k-leftDistance-2);
      }
      return leftDistance+1;
    }

    int rightDist = dfs(root.right, target, k);
    if (rightDist != -1) {
      if (rightDist + 1 == k) {
        result.add(root.val);
      } else {
        findDownwardNodes(root.left, k - rightDist - 2);
      }
      return rightDist + 1;
    }

    return -1;
  }

  public void findDownwardNodes(TreeNode root, int k){
    if (root == null) return;
    if(k==0){
      result.add(root.val);
      return;
    }
    findDownwardNodes(root.left, k-1);
    findDownwardNodes(root.right, k-1);
  }
}
