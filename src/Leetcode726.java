import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

class Leetcode726 {
  int i;
  public String countOfAtoms(String formula) {
    StringBuilder ans = new StringBuilder();
    i = 0;
    Map<String, Integer> count = parse(formula);
    for (String name: count.keySet()) {
      ans.append(name);
      int multiplicity = count.get(name);
      if (multiplicity > 1) {
        ans.append("" + multiplicity);
      }
    }
    return new String(ans);
  }

  public Map<String, Integer> parse(String formula) {
    int N = formula.length();
    Map<String, Integer> count = new TreeMap();
    while (i < N && formula.charAt(i) != ')') {
      if (formula.charAt(i) == '(') {
        i++;//改变全局索引位置
        for (Map.Entry<String, Integer> entry: parse(formula).entrySet()) {//获得本括号内的
          count.put(entry.getKey(), count.getOrDefault(entry.getKey(), 0) + entry.getValue());
        }
      } else {//不是‘（’
        int iStart = i++;
        while (i < N && Character.isLowerCase(formula.charAt(i))) {//获取当前元素
          i++;
        }
        String name = formula.substring(iStart, i);
        iStart = i;
        while (i < N && Character.isDigit(formula.charAt(i))) {//获取次数
          i++;
        }
        int multiplicity = iStart < i ? Integer.parseInt(formula.substring(iStart, i)) : 1;
        count.put(name, count.getOrDefault(name, 0) + multiplicity);
      }
    }
    int iStart = ++i;
    while (i < N && Character.isDigit(formula.charAt(i))) {//获取括号的次数
      i++;
    }
    if (iStart < i) {
      int multiplicity = Integer.parseInt(formula.substring(iStart, i));
      for (String key: count.keySet()) {
        count.put(key, count.get(key) * multiplicity);
      }
    }
    return count;
  }

  /*
  public String countOfAtoms(String formula) {
        int N = formula.length();
        Stack<Map<String, Integer>> stack = new Stack();
        stack.push(new TreeMap());

        for (int i = 0; i < N;) {
            if (formula.charAt(i) == '(') {遇到左括号时，将一个新的TreeMap推入栈顶。这表示开始了一个新的上下文，用于处理括号内的表达式
                stack.push(new TreeMap());
                i++;
            } else if (formula.charAt(i) == ')') {
                Map<String, Integer> top = stack.pop();////遇到右括号时，从栈中弹出顶部的Map（表示括号内的表达式）
                int iStart = ++i, multiplicity = 1;
                while (i < N && Character.isDigit(formula.charAt(i))) {
                    i++;
                }
                if (i > iStart) {
                    multiplicity = Integer.parseInt(formula.substring(iStart, i));
                }//获取数字
                for (String c: top.keySet()) {
                    int v = top.get(c);
                    stack.peek().put(c, stack.peek().getOrDefault(c, 0) + v * multiplicity);
                }
            } else {//获取元素以及次数
                int iStart = i++;
                while (i < N && Character.isLowerCase(formula.charAt(i))) {
                    i++;
                }
                String name = formula.substring(iStart, i);
                iStart = i;
                while (i < N && Character.isDigit(formula.charAt(i))) {
                    i++;
                }
                int multiplicity = i > iStart ? Integer.parseInt(formula.substring(iStart, i)) : 1;
                stack.peek().put(name, stack.peek().getOrDefault(name, 0) + multiplicity);
            }
        }

        StringBuilder ans = new StringBuilder();
        for (String name: stack.peek().keySet()) {
            ans.append(name);
            int multiplicity = stack.peek().get(name);
            if (multiplicity > 1) {
                ans.append("" + multiplicity);
            }
        }
        return new String(ans);
    }
   */
}
