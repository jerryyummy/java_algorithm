import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class LoadBalancer {
  public int[] getServerindex(int n, int[] arrival, int[] burstTime){
    int[][] requests = new int[arrival.length][2];
    for (int i = 0; i < arrival.length; i++) {
      requests[i][0] = i;
      requests[i][1] = arrival[i];
    }
    Arrays.sort(requests, new Comparator<int[]>() {
      @Override
      public int compare(int[] o1, int[] o2) {
        return o1[1]-o2[1];
      }
    });

    int[] servers = new int[n];

    int[] res = new int[arrival.length];
    Arrays.fill(res,-1);
    PriorityQueue<int[]> busyServers = new PriorityQueue<>(new Comparator<int[]>() {
      @Override
      public int compare(int[] o1, int[] o2) {
        return o1[1]-o2[1];
      }
    });
    PriorityQueue<int[]> availableServers = new PriorityQueue<>(new Comparator<int[]>() {
      @Override
      public int compare(int[] o1, int[] o2) {
        return o1[0]-o2[0];
      }
    });
    for (int i = 1; i <= n ; i++) {
      availableServers.add(new int[]{i,0});
    }

    for(int i = 0;i<requests.length;i++){
      int[] request = requests[i];
      int num = request[0];
      int arrivalTime = request[1];
      while (!busyServers.isEmpty() && busyServers.peek()[1] <= arrivalTime) {
        availableServers.add(busyServers.poll());
      }

      if (!availableServers.isEmpty()) {
        int[] server = availableServers.poll();
        res[i] = server[0];
        server[1] = arrivalTime + burstTime[num];
        busyServers.add(server);
      }
    }
    return res;
  }

  public static void main(String[] args) {
    LoadBalancer loadBalancer = new LoadBalancer();
    int[] res = loadBalancer.getServerindex(3,new int[]{2,4,1,8,9}, new int[]{7,9,2,4,5});
    for (int num : res) {
      System.out.println(num);
    }
  }

}
