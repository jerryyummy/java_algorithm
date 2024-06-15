import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Leetcode752 {
  public int openLock(String[] deadends, String target) {
    HashSet<String> set = new HashSet<>();
    set.addAll(Arrays.stream(deadends).toList());
    HashSet<String> visited = new HashSet<>();
    Queue<String> queue = new LinkedList<>();
    queue.offer("0000");
    visited.add("0000");
    int step = 0;
    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        String cur = queue.poll();
        if (set.contains(cur)) continue;
        if (cur.equals(target)) return step;
        for (int j = 0; j < 4; j++) {
          int num = cur.charAt(j) - '0';
          int temp1 = (num+1)%10;
          String next1 = cur.substring(0,j)+temp1+cur.substring(j+1);
          if (!visited.contains(next1)) {
            visited.add(next1);
            queue.offer(next1);
          }
          int temp2 = (num+9)%10;
          String next2 = cur.substring(0,j)+temp2+cur.substring(j+1);
          if (!visited.contains(next2)) {
            visited.add(next2);
            queue.offer(next2);
          }
        }
      }
      step++;
    }
    return -1;
  }
}
