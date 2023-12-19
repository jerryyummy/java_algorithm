import java.util.HashSet;
import java.util.Set;

class Node1 {
    public int val;
    public Node1 left;
    public Node1 right;
    public Node1 parent;
};

public class Leetcode1650 {
    public Node1 lowestCommonAncestor(Node1 p, Node1 q) {
        Set<Node1> set = new HashSet<>();
        while (p!=null || q!=null){
            if(p!=null){
                if (set.contains(p)) return p;
                set.add(p);
                p = p.parent;
            }
            if(q!=null){
                if (set.contains(q)) return q;
                set.add(q);
                q = q.parent;
            }
        }
        while (p!=null){
            if (set.contains(p)) return p;
            set.add(p);
            p = p.parent;
        }
        while (q!=null){
            if (set.contains(q)) return q;
            set.add(q);
            q = q.parent;
        }
        return null;
    }
}
