import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Leetcode2477 {
    HashMap<Integer, List<Integer>> map;
    boolean[] visited;
    int seats;
    public long minimumFuelCost(int[][] roads, int seats) {
        if(roads.length==0) return 0;
        this.seats = seats;
        map = new HashMap<>();
        visited = new boolean[roads.length+1];
        for (int i = 0; i < roads.length+1; i++) {
            map.put(i,new ArrayList<>());
        }
        for(int[] road:roads){
            map.get(road[0]).add(road[1]);
            map.get(road[1]).add(road[0]);
        }
        long res = 0;
        visited[0] = true;
        for(int next: map.get(0)){
            res+=dfs(next)[0];
        }
        return res;
    }

    public long[] dfs(int from){
        visited[from] = true;
        long cars = 0, fuel = 0, person = 1;
        for (int next:map.get(from)){
            if (!visited[next]){
                long[] temp = dfs(next);
                fuel += temp[0];
                person += temp[1];
            }
        }
        cars = person%seats>0? person/seats+1 :person/seats;
        return new long[]{fuel+cars,person};
    }
}
