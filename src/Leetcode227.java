import java.util.Stack;

public class Leetcode227 {
  public int calculate(String s) {
    if (s == null || s.isEmpty()) return 0;
    int last = 0, curr = 0, result = 0;
    char lastOperation = '+';
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (Character.isDigit(c)) {
        curr = curr * 10 + c - '0';
      }

      if ((!Character.isDigit(c) && c != ' ') || i == s.length()-1) {
        if (lastOperation == '+'){
          result += last;
          last = curr;
        }else if (lastOperation == '-'){
          result += last;
          last = -curr;
        }else if (lastOperation == '*'){
          last = last * curr;
        }else if (lastOperation == '/'){
          last = last / curr;
        }
        curr = 0;
        lastOperation = c;
      }
    }
    result += last;
    return result;
  }
}
