package snowflake;

import java.util.HashMap;
import java.util.Set;

public class SequantialString {

  public int[] countMinimumCharacters(String s, String[] arr){
    int[] res = new int[arr.length];
    for (int i = 0; i < res.length; i++) {
      res[i] = match(s,arr[i]);
    }
    return res;
  }

  public int match(String s, String t){
    HashMap<Character,Integer> s_map = new HashMap<>();
    HashMap<Character,Integer> t_map = new HashMap<>();
    for (int i = 0; i < t.length(); i++) {
      t_map.put(t.charAt(i),t_map.getOrDefault(t.charAt(i),0)+1);
    }
    int countMatch = 0;
    for (int i = 0; i < s.length(); i++) {
          s_map.put(s.charAt(i),s_map.getOrDefault(s.charAt(i),0)+1);
      if(!t_map.containsKey(s.charAt(i))) continue;
      else if (s_map.get(s.charAt(i))>t_map.get(s.charAt(i))){
        continue;
      }else {
        countMatch++;
        if(countMatch==t.length()) return i+1;
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    SequantialString solution = new SequantialString();
    int[] res = solution.countMinimumCharacters("064819848398",new String[]{"088", "364", "07"});
    for (int num:res){
      System.out.println(num);
    }
  }
}
