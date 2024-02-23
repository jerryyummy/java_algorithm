public class Leetcode151 {
  public String reverseWords(String s) {
    StringBuilder stringBuilder = new StringBuilder();
    int i = s.length()-1, j = i;
    while (i>=0 && j>=0){
      while (j>=0 && s.charAt(j)==' '){
        j--;
      }
      i = j;
      while (i>=0 && s.charAt(i)!=' '){
        i--;
      }
      if(j>=0){
        stringBuilder.append(s, i+1, j+1).append(" ");
      }
      j=i-1;
    }
    return stringBuilder.toString().trim();
  }
}
