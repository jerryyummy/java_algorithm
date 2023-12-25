import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Leetcode1851 {
    public int[] minInterval(int[][] intervals, int[] queries) {
        HashMap<Integer,Integer> map = new HashMap<>();
        Arrays.sort(intervals,(x, y)->(x[0]-y[0]));
        int arr[] = new int[queries.length],i=0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y)->((x[1]-x[0]) - (y[1]-y[0])));//按照间隔大小排序
        for(int j=0;j<arr.length;j++) arr[j]=queries[j];
        Arrays.sort(arr);
        for(int q : arr){
            while(i<intervals.length && intervals[i][0]<=q){
                pq.offer(intervals[i++]);//间隔小于left
            }
            while(pq.size()!=0 && pq.peek()[1]<q){//如果最小间隔的right小于q
                pq.poll();
            }
            map.put(q,(pq.size()==0)?-1:pq.peek()[1]-pq.peek()[0]+1);
        }
        for(i=0;i<queries.length;i++){
            queries[i]=map.get(queries[i]);
        }
        return queries;
    }
}
