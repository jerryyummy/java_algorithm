import java.util.ArrayDeque;
import java.util.Deque;

public class Leetcode150 {
  public int evalRPN(String[] tokens) {
    Deque<Integer> stack = new ArrayDeque<>();
    for (String token:tokens){
      if (token.equals("+")){
        int first = stack.remove();
        int second = stack.remove();
        stack.push(second+first);
      }else if (token.equals("-")){
        int first = stack.remove();
        int second = stack.remove();
        stack.push(second-first);
      }else if(token.equals("*")){
        int first = stack.remove();
        int second = stack.remove();
        stack.push(second*first);
      }else if (token.equals("/")){
        int first = stack.remove();
        int second = stack.remove();
        stack.push(second/first);
      }else{
        stack.push(Integer.parseInt(token));
      }
    }
    return stack.remove();
  }
}
