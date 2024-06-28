package tt;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class RemoveInvalidParentheses {
//  public static List<String> removeInvalidParentheses(String s) {
//    Set<String> result = new HashSet<>();
//    if (s == null) return result.stream().toList();
//
//    Set<String> visited = new HashSet<>();
//    Queue<String> queue = new LinkedList<>();
//    queue.add(s);
//    visited.add(s);
//
//    boolean found = false;
//
//    while (!queue.isEmpty()) {
//      int levelSize = queue.size();
//
//      for (int i = 0; i < levelSize; i++) {
//        String current = queue.poll();
//        if (isValid(current)) {
//          result.add(current);
//          found = true;
//        }
//
//        if (found) continue;
//
//        for (int j = 0; j < current.length(); j++) {
//          String next = current.substring(0, j) + current.substring(j + 1);
//          if (!visited.contains(next)) {
//            visited.add(next);
//            queue.add(next);
//          }
//        }
//      }
//
//      if (found) break;
//    }
//
//    return result.stream().toList();
//  }

  public static List<String> removeInvalidParentheses(String s) {
    Set<String> result = new HashSet<>();
    remove(s, result, 0, 0, new char[] {'(', ')'});
    return new ArrayList<>(result);
  }

  private static void remove(String s, Set<String> result, int last_i, int last_j, char[] par) {
    for (int stack = 0, i = last_i; i < s.length(); ++i) {
      if (s.charAt(i) == par[0]) stack++;
      if (s.charAt(i) == par[1]) stack--;
      if (stack >= 0) continue;
      for (int j = last_j; j <= i; ++j) {
        if (s.charAt(j) == par[1] && (j == last_j || s.charAt(j - 1) != par[1])) {
          remove(s.substring(0, j) + s.substring(j + 1, s.length()), result, i, j, par);
        }
      }
      return;
    }
    String reversed = new StringBuilder(s).reverse().toString();
    if (par[0] == '(') {
      remove(reversed, result, 0, 0, new char[] {')', '('});
    } else {
      result.add(reversed);
    }
  }

  private static boolean isValid(String s) {
    int count = 0;
    for (char c : s.toCharArray()) {
      if (c == '(') count++;
      if (c == ')') count--;
      if (count < 0) return false;
    }
    return count == 0;
  }

  public static void main(String[] args) {
    String s = ")(()())())";
    List<String> result = removeInvalidParentheses(s);
    System.out.println(result);
  }
}
