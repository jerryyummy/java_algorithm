import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Leetcode301 {

  private boolean isValid(String s) {
    int count = 0;
    for (char c : s.toCharArray()) {
      if (c == '(') count++;
      if (c == ')') count--;
      if (count < 0) return false;
    }
    return count == 0;
  }

  public List<String> removeInvalidParentheses(String s) {
    Set<String> res = new HashSet<>();
    if (s.isEmpty()) return res.stream().toList();

    boolean found = false;
    Set<String> visited = new HashSet<>();
    Queue<String> q = new LinkedList<>();
    q.offer(s);

    while (!q.isEmpty()) {
      int size = q.size();
      for (int i = 0; i < size; i++) {
        String cur = q.poll();
        if (isValid(cur)){
          res.add(cur);
          found = true;
        }

        if (found) continue;

        for (int j = 0; j < cur.length(); j++) {
          String next = cur.substring(0, j) + cur.substring(j + 1);
          if (!visited.contains(next)) {
            q.offer(next);
            visited.add(next);
          }
        }
      }

      if (found) break;
    }

    return new ArrayList<>(res);
  }
}
