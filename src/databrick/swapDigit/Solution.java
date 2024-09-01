package databrick.swapDigit;

import java.util.HashMap;
import java.util.List;

public class Solution {
  public int solve(List<Integer> numbers){
    int count = 0;
    HashMap<String, Integer> freq = new HashMap<>();
    for (int num:numbers){
      String numstr = String.valueOf(num);
      int len = numstr.length();
      count += freq.getOrDefault(numstr,0);

      for (int i = 0; i < len; i++) {
        for (int j = i+1; j < len; j++) {
          if (numstr.charAt(i) != numstr.charAt(j)){
            //swap(numstr[i],numstr[j]
            count += freq.getOrDefault(numstr,0);
          }
        }
      }
      freq.put(numstr,freq.getOrDefault(numstr,0)+1);
    }
    return count;
  }
}
