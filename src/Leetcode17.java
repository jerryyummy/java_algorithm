import java.util.*;

public class Leetcode17 {
  public List<String> letterCombinations(String digits) {
    Map<String, String[]> map = new HashMap<>();
    map.put("2",new String[]{"a","b","c"});
    map.put("3", new String[]{"d","e","f"});
    map.put("4",new String[]{"g","h","i"});
    map.put("5", new String[]{"j","k","l"});
    map.put("6", new String[]{"m","n","o"});
    map.put("7",new String[]{"p","q","r","s"});
    map.put("8", new String[]{"t","u","v"});
    map.put("9", new String[]{"w","x","y","z"});
    List<String> output = new ArrayList<>();
    if(digits.isEmpty()) return output;
    Deque<String> cur = new ArrayDeque<>();
    cur.offerLast("");
    for (int i = 0; i < digits.length(); i++) {
      int size = cur.size();
      for (int j = 0; j < size; j++) {
        String temp = cur.pollFirst();
        for(String str:map.get(digits.substring(i,i+1))){
          cur.offerLast(temp+str);
        }
      }
    }
    output.addAll(cur);
    return output;
  }
}
