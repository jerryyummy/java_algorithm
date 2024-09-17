public class Leetcode2414 {
  public int longestContinuousSubstring(String s) {
    if (s.length() == 1) return 1;
    int res = 0;
    int i = 0, j = 1;
    int n = s.length();
    while(j<n){
      if(s.charAt(j-1)+1==s.charAt(j)){
        j++;
      }else{
        i = j;
        j++;
      }
      res = Math.max(res,j-i);
    }

    return res;
  }
}
