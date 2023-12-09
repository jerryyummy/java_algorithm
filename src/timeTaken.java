import java.util.*;

/*
There are n persons numbered from 0 to n - 1 and a door. Each person can enter or exit through the door once, taking one second.

You are given a non-decreasing integer array arrival of size n, where arrival[i] is the arrival time of the ith person at the door. You are also given an array state of size n, where state[i] is 0 if person i wants to enter through the door or 1 if they want to exit through the door.

If two or more persons want to use the door at the same time, they follow the following rules:

If the door was not used in the previous second, then the person who wants to exit goes first.
If the door was used in the previous second for entering, the person who wants to enter goes first.
If the door was used in the previous second for exiting, the person who wants to exit goes first.
If multiple persons want to go in the same direction, the person with the smallest index goes first.
Return an array answer of size n where answer[i] is the second at which the ith person crosses the door.

Note that:

Only one person can cross the door at each second.
A person may arrive at the door and wait without entering or exiting to follow the mentioned rules.
 */
public class timeTaken {
    public int[] timeTaken(int[] arrival, int[] state) {
        Queue<Integer>[] arr = new Queue[2];
        arr[0] = new ArrayDeque<>();
        arr[1] = new ArrayDeque<>();
        int time = 0, direction = 1,i = 0, length = arrival.length;
        int[] res = new int[length];
        while (length-i+arr[0].size()+arr[1].size()!=0){
            while (i<length&& arrival[i]<=time){
                arr[state[i]].offer(i++);
            }
            direction = arr[0].isEmpty()?1:arr[1].isEmpty()?0:direction;
            if (!arr[direction].isEmpty()){
                res[arr[direction].poll()] = time;
            }
            time++;
        }
        return res;
    }
}
