public class Leetcode214 {
  public String shortestPalindrome(String s) {
    String revS = new StringBuilder(s).reverse().toString();
    int cnt = Kmp(revS, s);
    return new StringBuilder(s.substring(cnt)).reverse().toString() + s;
  }

  private int Kmp(String txt, String patt) {
    String newString = patt + "#" + txt;
    int[] pi = new int[newString.length()];
    int i = 1;
    int k = 0;

    while (i < newString.length()) {
      if (newString.charAt(i) == newString.charAt(k)) {
        k++;
        pi[i] = k;
        i++;
      } else {
        if (k > 0) {
          k = pi[k - 1];
        } else {
          pi[i] = 0;
          i++;
        }
      }
    }
    return pi[newString.length() - 1];
  }
}
