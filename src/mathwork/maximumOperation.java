package mathwork;

import java.util.HashMap;

public class maximumOperation {
  public long getMaximumOpeations(String s){
    int n = s.length();
    int ans = 0;
    HashMap<Character, Integer> map = new HashMap<Character, Integer>();

    int i = n-1;
    while(i>=1){
      if(s.charAt(i) == s.charAt(i-1)){
        ans += (n-1 - (i+1) + 1)-map.getOrDefault(s.charAt(i), 0);

        map = new HashMap<>();
        map.put(s.charAt(i), (n-1-(i-2)));
        i-=2;
      }else{
        map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        i-=1;
      }
    }
    return ans;
  }
}
