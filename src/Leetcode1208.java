public class Leetcode1208 {
  public int equalSubstring(String s, String t, int maxCost) {
    int n = s.length();
    int curr = 0;
    int res = Integer.MIN_VALUE;
    int i = 0, j = 0;
    while (j<n){
      curr += Math.abs(s.charAt(j) - t.charAt(j));
      while (j<n && i<=j && curr > maxCost){
        curr -= Math.abs(s.charAt(i) - t.charAt(i));
        i++;
      }
      res = Math.max(res, j-i+1);
      j++;
    }
    return res;
  }
}
