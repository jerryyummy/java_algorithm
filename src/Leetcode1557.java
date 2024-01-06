import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Leetcode1557 {
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        HashMap<Integer,Integer> in = new HashMap<>();
        for (List<Integer> edge :edges){
            in.put(edge.get(1),in.getOrDefault(edge.get(1),0)+1);
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (in.getOrDefault(i,0)==0) res.add(i);
        }
        return res;
    }
}
