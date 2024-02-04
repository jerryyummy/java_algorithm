import java.util.ArrayList;
import java.util.List;

public class Leetcode386 {
  public List<Integer> lexicalOrder(int n) {
    List<Integer> order = new ArrayList<>();
    int cur = 1;
    for(int i = 1; i <= n; i++) {
      order.add(cur);
      if(cur * 10 <= n) {
        cur *= 10;
      } else {
        if(cur >= n)
          cur /= 10;
        cur++;
        while(cur % 10 == 0)
          cur /= 10;
      }
    }
    return order;
  }

}
