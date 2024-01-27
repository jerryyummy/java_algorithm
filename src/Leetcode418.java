public class Leetcode418 {
  public int wordsTyping(String[] sentence, int rows, int cols) {
    String s = String.join(" ", sentence) + " ";//记录整个数组的长度和
    int start = 0, l = s.length();
    for (int i = 0; i < rows; i++) {
      start += cols;//先假设已经在一行放完了全部单词
      if (s.charAt(start % l) == ' ') {//如果恰好是两个单词之间
        start++;//在整个字符串位置+1；
      } else {
        while (start > 0 && s.charAt((start-1) % l) != ' ') {//直到回退到两个单词之间
          start--;//在整个字符串位置-1
        }
      }
    }

    return start / s.length();
  }

}
