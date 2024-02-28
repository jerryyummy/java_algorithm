/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

import java.util.LinkedList;
import java.util.Queue;

public class Leetcode117 {
//  public Node connect(Node root) {
//    if(root==null) return null;
//    Queue<Node> queue = new LinkedList<>();
//    queue.add(root);
//    while(!queue.isEmpty()){
//      Node node = queue.remove();
//      int size = queue.size();
//      if(node.left!=null) queue.add(node.left);
//      if(node.right!=null) queue.add(node.right);
//      for(int i = 0;i<size;i++){
//        Node temp = queue.remove();
//        node.next = temp;
//        node = temp;
//        if(node.left!=null) queue.add(node.left);
//        if(node.right!=null) queue.add(node.right);
//      }
//    }
//    return root;
//  }

//  public Node connect(Node root) {
//    if (root == null) return null;
//
//    Queue<Node> queue = new LinkedList<>();
//    queue.add(root);
//
//    while (!queue.isEmpty()) {
//      int size = queue.size(); // Number of nodes at the current level
//
//      for (int i = 0; i < size; i++) {
//        Node node = queue.poll(); // Get and remove the head of the queue
//
//        // If this is not the last node of the level, set its next to the next node in the queue
//        if (i < size - 1) {
//          node.next = queue.peek();
//        }
//
//        // Add child nodes to the queue for the next level
//        if (node.left != null) queue.add(node.left);
//        if (node.right != null) queue.add(node.right);
//      }
//    }
//
//    return root;
//  }

//  private ArrayList<Node> prevNodes = new ArrayList<>();
//
//  public Node connect(Node root) {
//    dfs(root, 0);
//    return root;
//  }
//
//  private void dfs(Node node, int depth) {
//    if (node == null) {
//      return;
//    }
//    if (depth == prevNodes.size()) { // This node is the first node of this level
//      prevNodes.add(node);
//    } else { // Connect the previous node at this depth to the current node
//      prevNodes.get(depth).next = node;
//      prevNodes.set(depth, node);
//    }
//    dfs(node.left, depth + 1); // Traverse left subtree
//    dfs(node.right, depth + 1); // Traverse right subtree
//  }
}