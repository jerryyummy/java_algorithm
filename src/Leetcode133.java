import java.util.*;
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

public class Leetcode133 {
    public Node cloneGraph(Node node){
        if (node==null) return null;
        Queue<Node> queue = new LinkedList<>();
        Map<Node,Node> map = new HashMap<>();
        queue.add(node);
        while (!queue.isEmpty()){
            Node cur = queue.remove();
            map.put(cur,new Node(cur.val));
            for (Node neighbor:cur.neighbors){
                if (!map.containsKey(neighbor)){
                    queue.add(neighbor);
                }
            }
        }

        for (Map.Entry<Node,Node> nodeEntry: map.entrySet()){
            for (Node neighbor:nodeEntry.getKey().neighbors){
                nodeEntry.getValue().neighbors.add(map.get(neighbor));
            }
        }

        return map.get(node);
    }
}
