import java.util.PriorityQueue;
import java.util.Queue;

public class Leetcode767 {
    public String reorganizeString(String s){
        int[] charCounts = new int[26];
        for (char c : s.toCharArray()) {
            charCounts[c - 'a']++;
        }
        // Max heap ordered by character counts
        Queue<int[]> pq = new PriorityQueue<int[]>((a, b) -> Integer.compare(b[1], a[1]));
        for (int i = 0; i < 26; i++) {
            if (charCounts[i] > 0) {
                pq.offer(new int[] {i + 'a', charCounts[i]});
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            int[] first = pq.poll();//找到目前剩余最多的字符
            if (sb.length() == 0 || first[0] != sb.charAt(sb.length() - 1)) {//如果为空或者不重复
                sb.append((char) first[0]);
                if (--first[1] > 0) {
                    pq.offer(first);
                }
            } else {
                if (pq.isEmpty()) {//如果没有其他字符替代
                    return "";
                }
                int[] second = pq.poll();
                sb.append((char) second[0]);
                if (--second[1] > 0) {
                    pq.offer(second);
                }

                pq.offer(first);
            }
        }

        return sb.toString();
    }
}
