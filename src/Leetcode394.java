import java.util.Objects;
import java.util.Stack;

public class Leetcode394 {
  public String decodeString(String s) {
    Stack<Integer> numStack = new Stack<>();
    Stack<String> strStack = new Stack<>();
    for (int i = 0; i < s.length(); i++) {
      String temp = s.substring(i,i+1);
      if (Character.isDigit(s.charAt(i))) {//如果当前是数字
        int number = Character.getNumericValue(s.charAt(i));
        while(Character.isDigit(s.charAt(i+1))){//持续获取数字
            number = number*10+Character.getNumericValue(s.charAt(i+1));
          i++;
        }
        numStack.push(number);
      } else if(!temp.equals("]")) strStack.push(temp);
      else {
        StringBuilder cur = new StringBuilder();
        while (!strStack.isEmpty() && !Objects.equals(strStack.peek(), "[")){
          cur.insert(0, strStack.pop());
        }
        strStack.pop();
        StringBuilder curr = new StringBuilder();
        int repeat = numStack.pop();
        for (int j = 0; j < repeat; j++) {
          curr.append(cur);
        }
        strStack.push(curr.toString());
      }
    }
    StringBuilder res = new StringBuilder();
    while (!strStack.isEmpty()){
      res.insert(0, strStack.pop());
    }
    return res.toString();
  }
}
