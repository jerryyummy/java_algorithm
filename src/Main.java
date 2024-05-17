import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Stack;

public class Main {

  public static void main(String[] args) {
    String s = "* + 3 4 5";
    String[] strings = s.split(" ");
    Stack<Float> numbers = new Stack<>();
    int n = strings.length;
    for (int i = n-1; i >=0 ; i--) {
      if (strings[i].equals("+") || strings[i].equals("-") || strings[i].equals("*") || strings[i].equals("/")){
        float temp = operate(strings[i], numbers.pop(), numbers.pop());
        numbers.push(temp);
      }else {
        float number = Float.parseFloat(strings[i]);
        numbers.push(number);
      }
    }
    System.out.println(numbers.pop());
  }

  public static float operate(String op, float a, float b){
    if (op.equals("+")){
      return a+b;
    }else if (op.equals("-")){
      return b-a;
    }else if (op.equals("*")){
      return b*a;
    }else {
      return b/a;
    }
  }

}

