import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {


  public static void main(String[] args) {
    System.out.println(solve("sss"));
    System.out.println(solve("한국어"));
    System.out.println(solve("哈哈哈"));
  }

  public static boolean solve(String s){
    byte[] bytes = s.getBytes();
    for (int i = 0; i < bytes.length; i++) {
      System.out.println(bytes[i]);
    }
    for (char c : s.toCharArray()) {
      if ((c >= '\u3130' && c <= '\u318F') || (c >= '\uAC00' && c <= '\uD7AF'))
        return true;
    }
    return false;
  }

}
