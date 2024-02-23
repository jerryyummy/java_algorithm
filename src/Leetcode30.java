import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Leetcode30 {
  public List<Integer> findSubstring(String s, String[] words) {
    List<Integer> res = new ArrayList<Integer>();
    int m = words.length, n = words[0].length(), ls = s.length();
    for (int i = 0; i < n; i++) {
      if (i + m * n > ls) {//验证字符串长度
        break;
      }
      Map<String, Integer> differ = new HashMap<String, Integer>();//表示窗口中单词频次和 words 中单词频次之差
      for (int j = 0; j < m; j++) {//遍历s字符串中从索引i开始的、长度为m*n的子串，将其中的单词及其出现次数加到differ中
        String word = s.substring(i + j * n, i + (j + 1) * n);
        differ.put(word, differ.getOrDefault(word, 0) + 1);
      }
      for (String word : words) {//遍历words数组，从differ中减去每个单词的计数。如果某单词的计数变为0，则从differ中移除该单词，表示窗口中的该单词与words数组中的该单词频次相匹配
        differ.put(word, differ.getOrDefault(word, 0) - 1);
        if (differ.get(word) == 0) {
          differ.remove(word);
        }
      }
      for (int start = i; start < ls - m * n + 1; start += n) {//通过移动窗口来寻找匹配的子串。每次移动长度为n，确保每次移动都是一个单词的长度
        if (start != i) {//如果start不等于i（即不是第一次尝试），则更新differ以反映窗口的变化：移入新单词
          String word = s.substring(start + (m - 1) * n, start + m * n);
          differ.put(word, differ.getOrDefault(word, 0) + 1);
          if (differ.get(word) == 0) {
            differ.remove(word);
          }
          word = s.substring(start - n, start);//移出旧单词，并相应地更新计数
          differ.put(word, differ.getOrDefault(word, 0) - 1);
          if (differ.get(word) == 0) {
            differ.remove(word);
          }
        }
        if (differ.isEmpty()) {
          res.add(start);
        }
      }
    }
    return res;
  }
}
