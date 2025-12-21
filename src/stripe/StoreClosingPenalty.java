package stripe;

import java.util.*;

public class StoreClosingPenalty {

    /**
     * Part 1: 计算给定关门时间的总罚分
     * 时间复杂度: O(N)
     */
    public static int computePenalty(String log, int closingTime) {
        // 将输入字符串按空格分割成数组
        // trim() 去除首尾空格，split("\\s+") 处理多个连续空格的情况
        String[] records = log.trim().split("\\s+");
        int n = records.length;
        int penalty = 0;

        for (int i = 0; i < n; i++) {
            if (i < closingTime) {
                // 开门时段 (0 ~ closingTime-1)
                // 如果没有客人 (N)，罚分 +1 (无效营业)
                if (records[i].equals("N")) {
                    penalty++;
                }
            } else {
                // 关门时段 (closingTime ~ n-1)
                // 如果有客人 (Y)，罚分 +1 (错失生意)
                if (records[i].equals("Y")) {
                    penalty++;
                }
            }
        }
        return penalty;
    }

    /**
     * Part 2: 寻找罚分最小的关门时间
     * 算法: 线性扫描 (One Pass / Prefix Sum 思想)
     * 时间复杂度: O(N)
     */
    public static int findBestClosingTime(String log) {
        String[] records = log.trim().split("\\s+");
        int n = records.length;

        // Step 1: 计算初始状态 (Closing Time = 0) 的罚分
        // 此时一直关门，罚分 = 所有 'Y' 的数量
        int currentPenalty = 0;
        for (String rec : records) {
            if (rec.equals("Y")) {
                currentPenalty++;
            }
        }

        int minPenalty = currentPenalty;
        int bestTime = 0;

        // Step 2: 线性遍历，逐步推迟关门时间 (从 0 推迟到 n)
        // 每次循环将第 i 小时从 "关门状态" 变为 "开门状态"
        for (int i = 0; i < n; i++) {
            if (records[i].equals("Y")) {
                // 这一小时有客人
                // 之前是关门(罚分)，现在变为开门(不罚分) -> 罚分减少
                currentPenalty--;
            } else {
                // 这一小时没客人 ('N')
                // 之前是关门(不罚分)，现在变为开门(无效营业) -> 罚分增加
                currentPenalty++;
            }

            // 当前的关门时间变成了 i + 1
            int currentTime = i + 1;

            // 更新最小值 (题目要求: 如果罚分相同，返回最早的时间，所以用 < 而不是 <=)
            if (currentPenalty < minPenalty) {
                minPenalty = currentPenalty;
                bestTime = currentTime;
            }
        }

        return bestTime;
    }

    /**
     * Part 3: 解析聚合日志并返回最佳关门时间列表
     * 逻辑: 简单的状态机解析 Token 流
     */
    public static List<Integer> getBestClosingTimes(String aggregateLog) {
        List<Integer> results = new ArrayList<>();

        // 使用 \\s+ 分割，处理换行符、制表符和多个空格
        String[] tokens = aggregateLog.trim().split("\\s+");

        List<String> currentLogBuffer = new ArrayList<>();
        boolean isCollecting = false;

        for (String token : tokens) {
            if (token.equals("BEGIN")) {
                // 开始新日志
                // 规则: 不可嵌套。如果之前正在收集，新的 BEGIN 会重置状态 (丢弃旧的)
                currentLogBuffer.clear();
                isCollecting = true;
            } else if (token.equals("END")) {
                // 结束当前日志
                if (isCollecting) {
                    // 将 Buffer 转回字符串，调用 Part 2 逻辑
                    String validLog = String.join(" ", currentLogBuffer);
                    // 只有非空日志才有意义 (视题目具体要求而定，这里假设至少会有 Y/N)
                    if (!validLog.isEmpty()) {
                        results.add(findBestClosingTime(validLog));
                    } else {
                        // 如果 BEGIN END 之间没有 Y/N，视作空日志，最佳时间通常是 0
                        results.add(0);
                    }

                    // 重置状态
                    currentLogBuffer.clear();
                    isCollecting = false;
                }
            } else if (token.equals("Y") || token.equals("N")) {
                // 只有在 BEGIN 之后才收集 Y/N
                if (isCollecting) {
                    currentLogBuffer.add(token);
                }
            } else {
                // 其他任何文本视为垃圾文本 (Garbage)，直接忽略
            }
        }

        return results;
    }

    // ==========================================
    // 测试入口 (Main)
    // ==========================================
    public static void main(String[] args) {
        // 测试数据: Y Y N Y
        String log1 = "Y Y N Y";

        System.out.println("--- Part 1 Test ---");
        // Time 0: Y(1)+Y(1)+N(0)+Y(1) = 3
        System.out.println("Time 0 Penalty: " + computePenalty(log1, 0)); // Expected: 3
        // Time 2: Y(0)+Y(0) | N(0)+Y(1) = 1
        System.out.println("Time 2 Penalty: " + computePenalty(log1, 2)); // Expected: 1
        // Time 4: Y(0)+Y(0)+N(1)+Y(0) = 1
        System.out.println("Time 4 Penalty: " + computePenalty(log1, 4)); // Expected: 1

        System.out.println("\n--- Part 2 Test ---");
        // 对于 "Y Y N Y", 最小罚分是 1, 对应时间 2 和 4。取最小时间 -> 2
        System.out.println("Best Time for 'Y Y N Y': " + findBestClosingTime(log1)); // Expected: 2

        // 边界测试
        System.out.println("Best Time for 'N N N': " + findBestClosingTime("N N N")); // Expected: 0
        System.out.println("Best Time for 'Y Y Y': " + findBestClosingTime("Y Y Y")); // Expected: 3

        System.out.println("\n--- Part 3 Test ---");
        String aggregateInput =
                "BEGIN Y Y N Y END \n" +           // Valid Log -> Result: 2
                        "GARBAGE TEXT \n" +                // Ignored
                        "BEGIN N N N END \n" +             // Valid Log -> Result: 0
                        "BEGIN Y BEGIN Y Y Y END";         // Nested -> Reset at 2nd BEGIN -> "Y Y Y" -> Result: 3

        List<Integer> batchResults = getBestClosingTimes(aggregateInput);
        System.out.println("Batch Results: " + batchResults);
        // Expected Output: [2, 0, 3]
    }
}