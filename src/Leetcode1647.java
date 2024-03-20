import java.util.Arrays;
import java.util.HashSet;

public class Leetcode1647 {
  public int minDeletions(String s) {
    int[] occur = new int[26];
    for (char c:s.toCharArray()){
      occur[c-'a']++;
    }
    Arrays.sort(occur);
    int res = 0;
    HashSet<Integer> set = new HashSet<>();
    for (int i = 25; i >= 0 ; i--) {
      int time = occur[i];
      while (set.contains(time) && time >0){
        time--;
      }
      if (time>0) set.add(time);
      res+=(occur[i]-time);
    }
    return res;
  }
}
