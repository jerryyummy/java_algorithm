import java.util.Stack;

public class Leetcode224 {
  public static int calculate(String s) {
    Stack<Integer> stack = new Stack<Integer>();
    int result = 0;
    int curr = 0;
    int sign = 1;
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (Character.isDigit(c)) {
        curr = curr * 10 + c - '0';
      }else if (c == '+'){
        result += sign*curr;
        curr = 0;
        sign = 1;
      }else if (c == '-'){
        result += sign*curr;
        curr = 0;
        sign = -1;
      }else if (c == '('){
        stack.push(result);
        stack.push(sign);
        sign = 1;
        result = 0;
        curr = 0;
      }else if (c == ')'){
        result += sign*curr;
        result *= stack.pop();
        result += stack.pop();
        curr = 0;
      }
    }
    result += sign*curr;
    int res = result;
    while(!stack.isEmpty()){
      res += stack.pop();
    }
    return res;
  }

  public static void main(String[] args) {
    System.out.println(calculate("- (3 + (4 + 5))"));
  }
}
