import java.util.ArrayDeque;
import java.util.Deque;

public class Leetcode735 {
  public int[] asteroidCollision(int[] asteroids) {
    Deque<Integer> deque = new ArrayDeque<>();
    for (int asteroid:asteroids){
      while (!deque.isEmpty() && deque.peekLast()*asteroid<0 && deque.peekLast()>0){
        if (deque.peekLast()+asteroid<0) deque.pollLast();
        else break;
      }
      if (!deque.isEmpty() && deque.peekLast()*asteroid<0 && deque.peekLast()>0){
        if (deque.peekLast()+asteroid>0) continue;
        else if (deque.peekLast()+asteroid==0){
          deque.pollLast();
          continue;
        }
      }
      deque.offerLast(asteroid);
    }
    int[] res = new int[deque.size()];
    int index = 0;
    while (!deque.isEmpty()){
      res[index++] = deque.pollFirst();
    }
    return res;
  }
}
