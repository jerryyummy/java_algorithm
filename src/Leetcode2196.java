import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Leetcode2196 {
    public TreeNode createBinaryTree(int[][] descriptions) {
        Map<Integer, TreeNode> nodeMap = new HashMap<>();
        Set<Integer> children = new HashSet<>();

        for (int[] description : descriptions) {
            int parentValue = description[0];
            int childValue = description[1];
            boolean isLeft = description[2] == 1;

            if (!nodeMap.containsKey(parentValue)) {
                nodeMap.put(parentValue, new TreeNode(parentValue));
            }
            if (!nodeMap.containsKey(childValue)) {
                nodeMap.put(childValue, new TreeNode(childValue));
            }

            if (isLeft) {
                nodeMap.get(parentValue).left = nodeMap.get(childValue);
            } else {
                nodeMap.get(parentValue).right = nodeMap.get(childValue);
            }

            children.add(childValue);
        }

        for (TreeNode node : nodeMap.values()) {
            if (!children.contains(node.val)) {
                return node;
            }
        }

        return null;
    }
}
