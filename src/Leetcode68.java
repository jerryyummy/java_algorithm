import java.util.ArrayList;
import java.util.List;

public class Leetcode68 {
  public List<String> fullJustify(String[] words, int maxWidth) {
    List<String> ans = new ArrayList<>();
    int i = 0;

    // 遍历所有单词
    while (i < words.length) {
      List<String> currentLine = getWords(i, words, maxWidth);
      i += currentLine.size(); // 更新下一行的开始单词索引
      ans.add(createLine(currentLine, i, words, maxWidth)); // 创建并添加当前行到结果中
    }

    return ans;
  }

  // 根据最大宽度获取当前行能包含的所有单词
  private List<String> getWords(int i, String[] words, int maxWidth) {
    List<String> currentLine = new ArrayList<>();
    int currLength = 0;

    // 确保当前行加上新单词后不会超过最大宽度
    while (i < words.length && currLength + words[i].length() <= maxWidth) {
      currentLine.add(words[i]);
      currLength += words[i].length() + 1; // 包括一个空格的长度
      i++;
    }

    return currentLine;
  }

  // 创建一行，根据是否是最后一行或者只有一个单词来调整空格
  private String createLine(List<String> line, int i, String[] words, int maxWidth) {
    int baseLength = -1;
    for (String word : line) {
      baseLength += word.length() + 1; // 计算基础长度（包括单词和一个空格）
    }

    int extraSpaces = maxWidth - baseLength; // 需要填充的额外空格数

    if (line.size() == 1 || i == words.length) { // 如果是最后一行或者行内只有一个单词
      return joinWords(line, " ") + repeatSpace(extraSpaces); // 右侧填充空格
    }

    int wordCount = line.size() - 1;
    int spacesPerWord = extraSpaces / wordCount; // 平均每个单词间隔的空格数
    int needsExtraSpace = extraSpaces % wordCount; // 前面几个单词需要额外添加一个空格

    for (int j = 0; j < needsExtraSpace; j++) {
      line.set(j, line.get(j) + " ");
    }

    for (int j = 0; j < wordCount; j++) {
      line.set(j, line.get(j) + repeatSpace(spacesPerWord + 1)); // 给除最后一个单词外的其他单词后面添加空格
    }

    return joinWords(line, ""); // 最后不需要空格，因为每个单词后的空格已经添加
  }

  // 手动实现String.join功能
  private String joinWords(List<String> words, String delimiter) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < words.size(); i++) {
      sb.append(words.get(i));
      if (i < words.size() - 1) {
        sb.append(delimiter); // 除了最后一个单词外，每个单词后都要加上分隔符
      }
    }
    return sb.toString();
  }

  // 手动实现" ".repeat(n)功能
  private String repeatSpace(int count) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < count; i++) {
      sb.append(" ");
    }
    return sb.toString();
  }
}
