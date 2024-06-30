public class Leetcode926 {
  public int minFlipsMonoIncr(String s) {
    int n = s.length();
    int flipsToZero = 0; // 翻转到 '0' 的最小次数
    int flipsToOne = 0;  // 翻转到 '1' 的最小次数

    for (int i = 0; i < n; i++) {
      if (s.charAt(i) == '0') {
        flipsToOne = Math.min(flipsToZero, flipsToOne) + 1;
      } else {
        flipsToOne = Math.min(flipsToZero, flipsToOne);
        flipsToZero++;
      }
    }

    return Math.min(flipsToZero, flipsToOne);
  }
}
