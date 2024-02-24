import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Leetcode49 {
  public List<List<String>> groupAnagrams(String[] strs) {
    HashMap<String, List<String>> map = new HashMap<>();
    for(String str : strs) {
      char[] temp = str.toCharArray();
      Arrays.sort(temp);
      String sortedStr = new String(temp);
      if(!map.containsKey(sortedStr)) {
        map.put(sortedStr, new ArrayList<>());
      }
      map.get(sortedStr).add(str);
    }
    return new ArrayList<>(map.values());
  }
}
