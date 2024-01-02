import java.util.*;

public class Leetcode2101 {
    public int maximumDetonation(int[][] bombs) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < bombs.length; i++) {
            map.put(i,new ArrayList<>());
            int x = bombs[i][0], y = bombs[i][1], r = bombs[i][2];
            for (int j = 0; j < bombs.length; j++) {
                if (j==i) continue;
                if (Math.pow(x-bombs[j][0],2)+Math.pow(y-bombs[j][1],2)<= (long)r*r){
                    map.get(i).add(j);
                }
            }
        }

        int res = 0;
        for (int i = 0; i < bombs.length; i++) {
            res = Math.max(res,bfs(map, bombs.length, i));
        }
        return res;
    }

    public int bfs(Map<Integer, List<Integer>> map, int length, int index){
        boolean[] visited = new boolean[length];
        Queue<Integer> queue = new LinkedList<>();
        visited[index] = true;
        int res = 0;
        queue.add(index);
        while (!queue.isEmpty()){
            int position = queue.remove();
            res++;
            for (int neighbor:map.get(position)){
                if (!visited[neighbor]){
                    queue.add(neighbor);
                    visited[neighbor] = true;
                }
            }
        }
        return res;
    }
}
