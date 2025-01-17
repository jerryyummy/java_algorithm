public class Leetcode443 {
  public int compress(char[] chars) {
    String s = "";
    int count = 0;
    char c = chars[0];
    int i = 0;
    while (i<chars.length) {
      if (chars[i] == c) {
        count++;
      }else{
        s += c;
        if (count > 1) {
          s += count;
        }
        c = chars[i];
        count = 1;
      }
      i++;
    }
    s += c;
    if (count > 1) {
      s += count;
    }
    for (int j = 0; j < s.length(); j++) {
      chars[j] = s.charAt(j);
    }
    return s.length();
  }
}
