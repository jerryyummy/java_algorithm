import java.util.ArrayDeque;
import java.util.Deque;

public class Leetcode402 {
  public String removeKdigits(String num, int k) {
    Deque<Character> stack = new ArrayDeque<>();
    for (char c : num.toCharArray()) {
      while (!stack.isEmpty() && stack.peek() > c && k > 0) {
        stack.pop();
        k--;
      }
      stack.push(c);
    }

    while (k > 0 && !stack.isEmpty()) {
      stack.pop();
      k--;
    }

    StringBuilder sb = new StringBuilder();
    while (!stack.isEmpty()) {
      sb.append(stack.pollLast()); // Use pollLast to respect stack's LIFO
    }

    // Remove leading zeros
    while (sb.length() > 1 && sb.charAt(0) == '0') {
      sb.deleteCharAt(0);
    }

    return sb.length() == 0 ? "0" : sb.toString();
  }
}
