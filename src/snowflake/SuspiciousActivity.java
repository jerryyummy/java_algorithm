package snowflake;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SuspiciousActivity {

  public static List<String> findSuspiciousUsers(List<String> logs, int threshold) {
    Map<String, Integer> userCount = new HashMap<>();

    // 遍历每条日志，统计每个用户的出现次数
    for (String log : logs) {
      String[] parts = log.split(" ");
      String sender = parts[0];
      String recipient = parts[1];

      // 发送者计数
      userCount.put(sender, userCount.getOrDefault(sender, 0) + 1);

      // 接收者计数
      if (!recipient.equals(sender)) {  // 避免同一个用户既是发送者又是接收者
        userCount.put(recipient, userCount.getOrDefault(recipient, 0) + 1);
      }
    }

    // 筛选出出现次数大于等于 threshold 的用户
    List<String> suspiciousUsers = new ArrayList<>();
    for (Map.Entry<String, Integer> entry : userCount.entrySet()) {
      if (entry.getValue() >= threshold) {
        suspiciousUsers.add(entry.getKey());
      }
    }

    // 按数值升序排序
    Collections.sort(suspiciousUsers, (a, b) -> Integer.parseInt(a) - Integer.parseInt(b));

    return suspiciousUsers;
  }
}
