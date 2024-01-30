package snowflake;

import java.util.HashMap;
import javafx.util.Pair;

public class MaximumSubstring {

  static HashMap<Pair<Integer, Integer>, Integer> map = new HashMap<>();
  public static int getMaxBeautifulSubstrings(String color) {
    return recur(color,0,color.length()-1);
  }

  private static int recur(String color, int start, int end){
    if (start>end) return 0;
    if (map.containsKey(new Pair<>(start,end))) return map.get(new Pair<>(start,end));
    char curr = '.';
    int res = 0;
    if(start==end) return 1;
    for (int i = start; i <= end ; i++) {
      if (color.charAt(i)=='.'){
        res = Math.max(res,(i-start+1)*(i-start+2)/2+recur(color,i+1,end));
      }else if (curr!='.' && color.charAt(i)!=curr){
        break;
      }else{
        curr = color.charAt(i);
        res = Math.max(res,(i-start+1)*(i-start+2)/2+recur(color,i+1,end));
      }
    }
    map.put(new Pair<>(start,end),res);
    return res;
  }


  public static void main(String[] args) {
    String color = "bb.ccc.";
    System.out.println(getMaxBeautifulSubstrings(color));
  }
}

