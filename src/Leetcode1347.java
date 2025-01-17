import java.util.HashMap;
import java.util.Map;

public class Leetcode1347 {
  public int minSteps(String s, String t) {
    HashMap<Character, Integer> map1 = new HashMap<>();
    HashMap<Character, Integer> map2 = new HashMap<>();
    for (int i = 0; i < s.length(); i++) {
      map1.put(s.charAt(i), map1.getOrDefault(s.charAt(i), 0) + 1);
    }
    for (int i = 0; i < t.length(); i++) {
      map2.put(t.charAt(i), map2.getOrDefault(t.charAt(i), 0) + 1);
    }

    int res = 0;
    for(Map.Entry<Character, Integer> entry : map1.entrySet()) {
      if(map2.containsKey(entry.getKey())) {
        map2.put(entry.getKey(), Math.max(0,map2.getOrDefault(entry.getKey(), 0) - entry.getValue()));
      }
    }
    for(Map.Entry<Character, Integer> entry : map2.entrySet()) {
      res += entry.getValue();
    }
    return res;
  }
}
