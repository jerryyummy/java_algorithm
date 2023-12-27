import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Leetcode1985 {
    public String kthLargestNumber(String[] nums, int k) {
        Queue<String> queue = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length()>o2.length()) return -1;
                else if (o1.length()<o2.length()) return 1;
                else{
                    int length = o1.length();
                    for (int i = 0; i < length; i++) {
                        if (o1.charAt(i)>o2.charAt(i)) return -1;
                        else if (o1.charAt(i)<o2.charAt(i)) return 1;
                    }
                    return 0;
                }
            }
        });
        for (String num:nums){
            queue.add(num);
            if (queue.size()>k) queue.remove();
        }
        return queue.peek();
    }
}
