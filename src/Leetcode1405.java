import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;

public class Leetcode1405 {

  public String longestDiverseString(int a, int b, int c) {

    int a_count = 0;
    int b_count = 0;
    int c_count = 0;

    int total = a + b + c;
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < total; i++) {
      if ((a >= b && a >= c && a_count < 2) || (b_count == 2 && a > 0) || (c_count == 2 && a > 0)) {
        sb.append('a');
        a--;
        a_count++;
        b_count = 0;
        c_count = 0;
      } else if ((b >= a && b >= c && b_count < 2) || (a_count == 2 && b > 0) || (c_count == 2
          && b > 0)) {
        sb.append('b');
        b--;
        b_count++;
        a_count = 0;
        c_count = 0;
      } else if ((c >= b && c >= a && c_count < 2) || (b_count == 2 && c > 0) || (a_count == 2
          && c > 0)) {
        sb.append('c');
        c--;
        c_count++;
        b_count = 0;
        a_count = 0;
      }
    }
    return sb.toString();
  }

//  public String longestDiverseString(int a, int b, int c) {
//    StringBuilder sb = new StringBuilder();
//    Queue<Pair<Character, Integer>> pq = new PriorityQueue<>((c1, c2) -> (c2.getValue() - c1.getValue()));
//
//    if (a>0) {
//      pq.add(new Pair<Character, Integer>('a', a));
//    }
//    if (b>0) {
//      pq.add(new Pair<Character, Integer>('b', b));
//    }
//    if (c>0) {
//      pq.add(new Pair<Character, Integer>('c', c));
//    }
//
//    sb.append("zz");
//
//    while(!pq.isEmpty()) {
//      Pair<Character, Integer> temp = null;
//      if(pq.peek().getKey() == sb.charAt(sb.length()-1) && pq.peek().getKey() == sb.charAt(sb.length()-2)) {
//        temp = pq.poll();
//        if(pq.isEmpty()) {
//          break;
//        }
//      }
//
//      sb.append(pq.peek().getKey());
//      int val = ((int)pq.peek().getValue())-1;
//      char key = pq.poll().getKey();
//      if(val > 0) {
//        pq.add(new Pair<Character, Integer>(key, val));
//      }
//
//      if(temp != null) {
//        pq.add(temp);
//      }
//    }
//
//    return sb.substring(2).toString();
//  }

}
