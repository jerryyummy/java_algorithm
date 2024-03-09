public class Leetcode5 {
  int maxLen = 0;
  int lo = 0;
  public String longestPalindrome(String s) {
    if(s.length()<2) return s;
    for(int i = 0; i<s.length();i++){
      expand(s,i,i);
      expand(s,i,i+1);
    }
    return s.substring(lo,lo+maxLen);
  }

  public void expand(String s, int start, int end){
    while(start>=0 && end<s.length()&&s.charAt(start)==s.charAt(end)){
      start--;
      end++;
    }
    if(maxLen<end-start-1){
      maxLen = end-start-1;
      lo = start+1;
    }
  }

  /*
  public String longestPalindrome(String s){
        if (Objects.equals(s,"")||s.length()<2) return s;
        boolean[][] dp = new boolean[s.length()][s.length()];
        int maxLen = 1, start = 0, end = 0;
        for (int i = 1; i < s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (s.charAt(i)==s.charAt(j)&&(i-j<=2 || dp[j+1][i-1])){
                    dp[j][i] = true;
                    if (i-j+1>maxLen){
                        start=j;
                        end=i;
                        maxLen=(end-start+1);
                    }
                }
            }
        }
        return s.substring(start,end+1);
    }
   */
}
