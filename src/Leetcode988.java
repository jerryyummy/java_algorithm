public class Leetcode988 {
  String res = "";

  public String smallestFromLeaf(TreeNode root) {
    dfs(root, new StringBuilder());
    return res;
  }

  public void dfs(TreeNode root, StringBuilder sb) {
    if (root == null) {
      if (res.isEmpty()){
        res = sb.reverse().toString();
        return;
      }else if (sb.reverse().toString().compareTo(res) < 0) {
        res = sb.reverse().toString();
        return;
      }
    }
    sb.append('a' +root.val);
    dfs(root.left, sb);
    dfs(root.right, sb);
    sb.setLength(sb.length()-1);
  }

}
