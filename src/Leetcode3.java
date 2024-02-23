import java.util.HashSet;

public class Leetcode3 {
  public int lengthOfLongestSubstring(String s) {
    HashSet<Character> set = new HashSet<>();
    int i = 0, j = 0;
    int length = 0;
    while(j<s.length()){
      if(set.isEmpty() || !set.contains(s.charAt(j))){
        length = Math.max(length,j-i);
        j++;
      }else{
        while (s.charAt(i)!=s.charAt(j)){
          i++;
        }
        i++;
      }
    }
    return length;
  }
}
