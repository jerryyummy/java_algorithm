import java.util.Arrays;
import java.util.Comparator;

public class Leetcode1288 {
    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0]!=o2[0]){
                    return o1[0]<o2[0]?-1:1;
                }else{
                    return (o2[1]-o2[0])-(o1[1]-o1[0]);
                }
            }
        });
        int res = 0;
        int endTime = Integer.MIN_VALUE;
        for (int i = 0; i < intervals.length; i++) {
            if (intervals[i][1]<=endTime){
                continue;
            }else {
                res++;
                endTime = intervals[i][1];
            }
        }
        return res;
    }
}
