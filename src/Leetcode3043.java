import java.util.HashSet;

public class Leetcode3043 {
  public int longestCommonPrefix(int[] arr1, int[] arr2) {
    HashSet<Integer> hs = new HashSet<>();
    for(int i = 0; i<arr1.length; i++) {
      String s = arr1[i]+"";
      int num = 0;
      for(int j = 0; j<s.length(); j++) {
        num = num*10 + (int)(s.charAt(j) - '0');
        hs.add(num);
      }
    }
    int res = 0;
    for(int i = 0; i<arr2.length; i++) {
      String s = arr2[i]+"";
      int num = 0;
      for(int j = 0; j<s.length(); j++) {
        num = num*10 + (int)(s.charAt(j) - '0');
        if(hs.contains(num)) {
          res = Math.max(res, (num+"").length());
        }
      }
    }
    return res;
  }
}
