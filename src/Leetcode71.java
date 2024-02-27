import java.util.ArrayDeque;
import java.util.Deque;

public class Leetcode71 {
  public String simplifyPath(String path) {
    Deque<String> stack = new ArrayDeque<>();
    String[] paths = path.split("/");
    for(String str:paths){
      if (str.isEmpty() || str.equals(".")) continue;
      else if(str.equals("..")) {
        if (!stack.isEmpty()) stack.pop();
      }
      else{
        stack.push(str);
      }
    }
    StringBuilder stringBuilder = new StringBuilder();
    while (!stack.isEmpty()){
      stringBuilder.append("/").append(stack.pollLast());
    }
    return stringBuilder.toString().isEmpty()? "/":stringBuilder.toString();
  }
}
