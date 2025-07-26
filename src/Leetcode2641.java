import java.util.LinkedList;
import java.util.Queue;

public class Leetcode2641 {
    public TreeNode replaceValueInTree(TreeNode root) {
        if (root == null) {
            return root;
        }

        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.offer(root);
        int currentLevelSum = root.val;

        while (!nodeQueue.isEmpty()) {
            int levelSize = nodeQueue.size();
            int nextLevelSum = 0;

            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = nodeQueue.poll();
                // Update node value to cousin sum
                currentNode.val = currentLevelSum - currentNode.val;

                // Calculate sibling sum
                int siblingSum =
                        (currentNode.left != null ? currentNode.left.val : 0) +
                                (currentNode.right != null ? currentNode.right.val : 0);

                if (currentNode.left != null) {
                    nextLevelSum += currentNode.left.val; // Accumulate next level sum
                    currentNode.left.val = siblingSum; // Update left child's value
                    nodeQueue.offer(currentNode.left); // Add to queue for next level
                }
                if (currentNode.right != null) {
                    nextLevelSum += currentNode.right.val; // Accumulate next level sum
                    currentNode.right.val = siblingSum; // Update right child's value
                    nodeQueue.offer(currentNode.right); // Add to queue for next level
                }
            }

            currentLevelSum = nextLevelSum; // Update current level sum for next iteration
        }
        return root;
    }
}
