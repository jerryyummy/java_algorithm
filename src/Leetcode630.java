import java.util.Arrays;
import java.util.PriorityQueue;

public class Leetcode630 {
    public int scheduleCourse(int[][] courses) {
//        Arrays.sort(courses, (a, b) -> a[1] - b[1]);
//        int time = 0, count = 0;
//        for (int i = 0; i < courses.length; i++) {
//            if (time + courses[i][0] <= courses[i][1]) {
//                time += courses[i][0];
//                count++;
//            } else {
//                int max_i = i;
//                for (int j = 0; j < i; j++) {
//                    if (courses[j][0] > courses[max_i][0])
//                        max_i = j;
//                }
//                if (courses[max_i][0] > courses[i][0]) {
//                    time += courses[i][0] - courses[max_i][0];
//                }
//                courses[max_i][0] = -1;
//            }
//        }
//        return count;

        Arrays.sort(courses, (a,b)->a[1]-b[1]);
        int time = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->b-a);
        for (int[] course : courses) {
            if(time + course[0] < course[1]){
                pq.add(course[0]);
                time += course[0];
            }else if(!pq.isEmpty() && pq.peek() > course[0]){
                time += course[0] - pq.poll();
                pq.add(course[0]);
            }
        }
        return pq.size();
    }
}
