package adobe;

import java.util.ArrayList;
import java.util.List;

public class Romanizer {
  // 罗马数字和对应的阿拉伯数字
  private static final int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
  private static final String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

  public static List<String> romanizer(int[] numbers) {
    List<String> result = new ArrayList<>();

    // 遍历每个数字，将其转换为罗马数字
    for (int num : numbers) {
      result.add(intToRoman(num));
    }

    return result;
  }

  // 将一个整数转换为罗马数字
  private static String intToRoman(int num) {
    StringBuilder roman = new StringBuilder();

    // 从大到小遍历罗马数字的值
    for (int i = 0; i < values.length; i++) {
      // 尽可能多地减去当前的罗马数字值
      while (num >= values[i]) {
        num -= values[i];
        roman.append(symbols[i]);
      }
    }

    return roman.toString();
  }

  public static void main(String[] args) {
    int[] numbers = {1, 49, 23};
    List<String> romanNumbers = romanizer(numbers);

    // 打印结果
    System.out.println(romanNumbers); // 输出: [I, XLIX, XXIII]
  }
}
