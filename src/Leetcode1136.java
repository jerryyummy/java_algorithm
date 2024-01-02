import java.util.*;

public class Leetcode1136 {
    public int minimumSemesters(int n, int[][] relations) {
        HashMap<Integer, List<Integer>> neighbors = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            neighbors.put(i,new ArrayList<>());
        }
        int[] in = new int[n+1];
        for (int[] relation:relations){
            neighbors.get(relation[0]).add(relation[1]);
            in[relation[1]]++;
        }

        int step = 0;
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n+1];
        for (int i = 1; i <= n; i++) {
            if (in[i]==0){
                queue.add(i);
                visited[i] = true;
            }
        }

        int reachedCourse = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int index = queue.remove();
                reachedCourse++;
                for (int neighbor:neighbors.get(index)){
                    in[neighbor]--;
                    if (in[neighbor]==0 && !visited[neighbor]){
                        queue.add(neighbor);
                    }
                }
            }
            step++;
        }
        return reachedCourse==n?step:-1;
    }
}
