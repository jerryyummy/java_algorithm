package snowflake;

import java.util.ArrayList;
import java.util.List;

public class WorkSchedule {
  public static List<String> findSchedules(int workHours, int dayHours, String pattern) {
    // 将结果存储到一个列表中
    List<String> results = new ArrayList<>();
    // 计算已经填充的小时数
    int filledHours = 0;

    for (char c : pattern.toCharArray()) {
      if (c != '?') {
        filledHours += c - '0';
      }
    }

    // 剩余需要分配的小时数
    int remainingHours = workHours - filledHours;

    // 开始递归回溯寻找所有可能的日程安排
    backtrack(pattern.toCharArray(), 0, remainingHours, dayHours, results);

    // 将结果按字典顺序排序
    results.sort(String::compareTo);

    return results;
  }

  private static void backtrack(char[] schedule, int index, int remainingHours, int dayHours, List<String> results) {
    if (index == schedule.length) {
      if (remainingHours == 0) {
        results.add(new String(schedule));
      }
      return;
    }

    if (schedule[index] == '?') {
      // 尝试从 0 到 dayHours 的每一个可能值
      for (int i = 0; i <= dayHours; i++) {
        if (remainingHours - i >= 0) {
          schedule[index] = (char) ('0' + i);  // 填入 i
          backtrack(schedule, index + 1, remainingHours - i, dayHours, results);
          schedule[index] = '?';  // 回溯
        }
      }
    } else {
      // 当前字符不是问号，继续下一个
      backtrack(schedule, index + 1, remainingHours, dayHours, results);
    }
  }
}
