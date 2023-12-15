import java.util.*;

public class Leetcode987 {
    public List<List<Integer>> verticalTraversal(TreeNode root){
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Map<Integer, List<Integer>> hash = new HashMap<>();
        Queue<Integer> qCol = new LinkedList<>();
        Queue<TreeNode> qNode = new LinkedList<>();

        qCol.offer(0);
        qNode.offer(root);

        while (!qCol.isEmpty()) {
            int size = qCol.size();
            Map<Integer, List<Integer>> temp = new HashMap<>();//record this level of column index
            for (int i = 0; i < size; i++) {
                int c = qCol.poll();// 获取当前节点的column
                TreeNode node = qNode.poll();// 获取当前节点

                temp.putIfAbsent(c, new ArrayList<>());
                temp.get(c).add(node.val);//加入对应列集合

                if (node.left != null) {
                    qCol.offer(c - 1);
                    qNode.offer(node.left);
                }
                if (node.right != null) {
                    qCol.offer(c + 1);
                    qNode.offer(node.right);
                }
            }
            for( Map.Entry<Integer,List<Integer>> entry :temp.entrySet()){//对当前列进行排序
                Collections.sort(entry.getValue());
                if (!hash.containsKey(entry.getKey())) {
                    hash.put(entry.getKey(), entry.getValue());
                }else{
                    hash.get(entry.getKey()).addAll(entry.getValue());
                }
            }
        }

        for (int i = Collections.min(hash.keySet()); i <= Collections.max(hash.keySet()); i++) {
            res.add(hash.get(i));
        }
        return res;
    }

}
