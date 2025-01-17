import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leetcode1408 {
  public int[] computeLPS(String pat) {
    int n = pat.length();
    int[] lps = new int[n];
    int len = 0;

    for (int i = 1; i < n; ++i) {
      while (len > 0 && pat.charAt(i) != pat.charAt(len)) {
        len = lps[len - 1];
      }
      if (pat.charAt(i) == pat.charAt(len)) {
        len++;
        lps[i] = len;
      }
    }
    return lps;
  }

  public boolean isPatternInText(String txt, String pat) {
    if (pat.isEmpty()) return true;
    if (txt.length() < pat.length()) return false;

    int[] lps = computeLPS(pat);
    int i = 0;
    int j = 0;

    while (i < txt.length()) {
      if (txt.charAt(i) == pat.charAt(j)) {
        i++;
        j++;
        if (j == pat.length()) {
          return true;
        }
      } else {
        if (j != 0) {
          j = lps[j - 1];
        } else {
          i++;
        }
      }
    }
    return false;
  }

  public List<String> stringMatching(String[] words) {
    List<String> res = new ArrayList<>();
    Arrays.sort(words, (a, b) -> Integer.compare(a.length(), b.length()));

    for (int i = 0; i < words.length; ++i) {
      for (int j = i + 1; j < words.length; ++j) {
        if (isPatternInText(words[j], words[i])) {
          res.add(words[i]);
          break;
        }
      }
    }
    return res;
  }
}
