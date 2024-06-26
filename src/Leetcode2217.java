public class Leetcode2217 {
  public long[] kthPalindrome(int[] queries, int intLength) {
    int n=queries.length;
    long ans[]=new long[n];
    int ans_i=0;

    int palindrome_present = ( intLength+1 )/2 ;

    long l=(long)Math.pow(10,palindrome_present-1);
    long r=(long)Math.pow(10,palindrome_present)-1;
    for(int q:queries){
      if(q<=(r-l+1)){
        String left_half=Long.toString(l+q-1);
        String right_half=(new StringBuilder(left_half)).reverse().toString();
        ans[ans_i]=Long.parseLong( left_half+right_half.substring(intLength % 2 ) );
      }
      else{
        ans[ans_i]=-1;
      }
      ans_i++;
    }
    return ans;
  }
}
