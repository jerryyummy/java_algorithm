import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class Main {
  public static void main(String[] args) {
    Main main = new Main();

    Scanner in = new Scanner(System.in);

    String s = "bbb";

    System.out.println(main.solve(s));

  }

  public int solve(String s){
    HashSet<Character> set = new HashSet<>();
    int i = 0, j = 0;
    int res = 0;
    while(j<s.length()){
      if(set.isEmpty() || !set.contains(s.charAt(j))){
        res = Math.max(res,j-i+1);
        set.add(s.charAt(j));
        j++;
      }else{
        while (s.charAt(i)!=s.charAt(j)){
          set.remove(s.charAt(i));
          i++;
        }
        i++;
        j++;
      }
    }
    return res;
  }
}

