import java.util.Stack;

public class Leetcode1209 {
//  public String removeDuplicates(String s, int k) {
//    StringBuilder sb = new StringBuilder(s);
//    Stack<Integer> counts = new Stack<>();
//    for (int i = 0; i < sb.length(); ++i) {
//      if (i == 0 || sb.charAt(i) != sb.charAt(i - 1)) {
//        counts.push(1);
//      } else {
//        int incremented = counts.pop() + 1;
//        if (incremented == k) {
//          sb.delete(i - k + 1, i + 1);
//          i = i - k;
//        } else {
//          counts.push(incremented);
//        }
//      }
//    }
//    return sb.toString();
//  }

  class Pair {
    int cnt;
    char ch;
    public Pair(int cnt, char ch) {
      this.ch = ch;
      this.cnt = cnt;
    }
  }
  public String removeDuplicates(String s, int k) {
    Stack<Pair> counts = new Stack<>();
    for (int i = 0; i < s.length(); ++i) {
      if (counts.empty() || s.charAt(i) != counts.peek().ch) {
        counts.push(new Pair(1, s.charAt(i)));
      } else {
        if (++counts.peek().cnt == k) {
          counts.pop();
        }
      }
    }
    StringBuilder b = new StringBuilder();
    while (!counts.empty()) {
      Pair p = counts.pop();
      for (int i = 0; i < p.cnt; i++) {
        b.append(p.ch);
      }
    }
    return b.reverse().toString();
  }

}
