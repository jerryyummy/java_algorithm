package snowflake;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StringPermutations {

  public static void main(String[] args) {
    String input = "bbc";
    List<String> permutations = generatePermutations(input);
    System.out.println(permutations);
  }

  public static List<String> generatePermutations(String input) {
    Set<String> result = new HashSet<>(); // 使用HashSet防止重复
    result.add(""); // 包含空字符串

    for (int i = 0; i < input.length(); i++) {
      Set<String> temp = new HashSet<>(); // 临时HashSet存储新排列
      for (String str : result) {
        for (int j = 0; j <= str.length(); j++) {
          // 在每个可能的位置插入当前字符
          temp.add(str.substring(0, j) + input.charAt(i) + str.substring(j));
          // 避免重复的排列
        }
      }
      result.addAll(temp); // 将所有新排列添加到结果中
    }

    return new ArrayList<>(result); // 将HashSet转换为List以返回结果
  }
}


