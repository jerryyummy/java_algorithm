import java.util.Deque;
import java.util.Objects;
import java.util.Stack;

public class Leetcode394 {
  public String decodeString(String s) {
    Stack<Integer> numStack = new Stack<>();
    Stack<StringBuilder> strStack = new Stack<>();
    StringBuilder currentString = new StringBuilder();
    int currentNumber = 0;

    for(char c : s.toCharArray()) {
      if (Character.isDigit(c)) {
        currentNumber = currentNumber * 10 + Character.getNumericValue(c);
      } else if (c == '[') {
        numStack.push(currentNumber);
        strStack.push(currentString);
        currentString = new StringBuilder();
        currentNumber = 0;
      } else if (c == ']') {
        StringBuilder temp = currentString;
        currentString = strStack.pop();
        int repeatTimes = numStack.pop();
        for (int i = 0; i < repeatTimes; i++) {
          currentString.append(temp);
        }
      } else {
        currentString.append(c);
      }
    }

    return currentString.toString();
  }
}
