import java.util.HashMap;
import java.util.Map;

public class Leetcode1400 {
  public boolean canConstruct(String s, int k) {
    if(s.length() == 0 || k > s.length()) return false;
    Map<Character, Integer> map = new HashMap<>();
    for(int i = 0; i < s.length(); i++){
      char c = s.charAt(i);
      map.put(c, map.getOrDefault(c, 0) + 1);
    }

    int oddCount = 0;
    for(Map.Entry<Character, Integer> entry : map.entrySet()){
      if(entry.getValue() % 2 != 0) oddCount++;
    }
    if(oddCount > k) return false;
    return true;
  }
}
