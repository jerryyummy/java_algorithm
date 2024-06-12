import java.util.*;

public class Leetcode2492 {
    public int minScore(int n, int[][] roads) {
        HashMap<Integer, List<int[]>> map = new HashMap<>();
        for (int i = 1; i < n+1; i++) {
            map.put(i,new ArrayList<>());
        }
        for (int[] road:roads){
            map.get(road[0]).add(new int[]{road[1],road[2]});
            map.get(road[1]).add(new int[]{road[0],road[2]});
        }
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        queue.add(1);
        int res = Integer.MAX_VALUE;
        while (!queue.isEmpty()){
            int index = queue.remove();
            for (int[] neighbor:map.get(index)){
                int next = neighbor[0];
                int length = neighbor[1];
                res = Math.min(res,length);
                if (!visited[next-1]){
                    visited[next-1] = true;
                    queue.add(next);
                }
            }
        }
        return res;
    }
}