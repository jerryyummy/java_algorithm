import java.util.Stack;

public class Leetcode946 {
  public boolean validateStackSequences(int[] pushed, int[] popped) {
    int i = 0, j = 0;
    Stack<Integer> stack = new Stack<>();
    while (i<pushed.length || j<popped.length){
      if (stack.isEmpty()) stack.push(pushed[i++]);
      else if (stack.peek() == popped[j]){
        stack.pop();
        j++;
      }else{
        if (i==pushed.length) break;
        stack.push(pushed[i++]);
      }
    }
    return stack.isEmpty();
  }
}
