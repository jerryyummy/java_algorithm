package stripe;

import java.util.*;
import java.util.stream.Collectors;

public class FraudDetection {

    // 定义一个内部类来保存交易信息，方便后续处理
    static class Transaction {
        int id; // 原始列表中的索引
        String merchantId;
        int amount;
        String customerId;
        int hour;

        // 对应的规则信息
        int minTransAmount;
        int multFactor;
        int addFactor;
        int penalty;

        public Transaction(int id, String transStr, String ruleStr) {
            this.id = id;
            String[] tParts = transStr.split(",");
            this.merchantId = tParts[0];
            this.amount = Integer.parseInt(tParts[1]);
            this.customerId = tParts[2];
            this.hour = Integer.parseInt(tParts[3]);

            String[] rParts = ruleStr.split(",");
            this.minTransAmount = Integer.parseInt(rParts[0]);
            this.multFactor = Integer.parseInt(rParts[1]);
            this.addFactor = Integer.parseInt(rParts[2]);
            this.penalty = Integer.parseInt(rParts[3]); // 题目第二张图显示有第4个参数
        }
    }

    public static List<String> calculateFraudScores(List<String> transactions_list,
                                                    List<String> rules_list,
                                                    List<String> merchants_list) {

        // 1. 初始化商家分数 Map
        Map<String, Integer> merchantScores = new HashMap<>();
        for (String m : merchants_list) {
            String[] parts = m.split(",");
            merchantScores.put(parts[0], Integer.parseInt(parts[1]));
        }

        // 解析所有交易和规则，存入列表
        List<Transaction> allTransactions = new ArrayList<>();
        for (int i = 0; i < transactions_list.size(); i++) {
            allTransactions.add(new Transaction(i, transactions_list.get(i), rules_list.get(i)));
        }

        // ==========================================
        // STEP 1: Multiplicative Pass (乘法规则)
        // ==========================================
        for (Transaction t : allTransactions) {
            if (t.amount > t.minTransAmount) {
                int currentScore = merchantScores.get(t.merchantId);
                merchantScores.put(t.merchantId, currentScore * t.multFactor);
            }
        }

        // ==========================================
        // STEP 2: Additive Pass (累加规则 - 用户频率)
        // ==========================================
        // Key: "merchantId|customerId", Value: List of additive factors seen so far
        Map<String, List<Integer>> custHistory = new HashMap<>();

        for (Transaction t : allTransactions) {
            String key = t.merchantId + "|" + t.customerId;
            custHistory.putIfAbsent(key, new ArrayList<>());
            List<Integer> history = custHistory.get(key);

            // 将当前的 additive factor 加入历史记录
            history.add(t.addFactor);

            int count = history.size();
            int currentScore = merchantScores.get(t.merchantId);

            if (count == 3) {
                // 如果正好是第3次，加上前3次所有的 factor
                int sum = 0;
                for (int factor : history) {
                    sum += factor;
                }
                merchantScores.put(t.merchantId, currentScore + sum);
            } else if (count > 3) {
                // 如果是第3次以后，只加当前的 factor
                merchantScores.put(t.merchantId, currentScore + t.addFactor);
            }
            // 注意：count < 3 时不执行任何操作
        }

        // ==========================================
        // STEP 3: Penalty Pass (惩罚规则 - 小时频率)
        // ==========================================
        // 需要按 (Merchant, Customer, Hour) 分组
        // Key: "merchantId|customerId|hour", Value: List of Transactions
        Map<String, List<Transaction>> hourlyGroups = new HashMap<>();

        for (Transaction t : allTransactions) {
            String key = t.merchantId + "|" + t.customerId + "|" + t.hour;
            hourlyGroups.putIfAbsent(key, new ArrayList<>());
            hourlyGroups.get(key).add(t);
        }

        // 遍历每一个分组
        for (List<Transaction> group : hourlyGroups.values()) {
            // 只有当该组交易数量 >= 3 时才触发逻辑
            if (group.size() >= 3) {
                // 题目要求：该组内的每一笔交易都要计算惩罚 (Includes first, second...)
                for (Transaction t : group) {
                    int currentScore = merchantScores.get(t.merchantId);

                    if (t.hour >= 12 && t.hour <= 17) {
                        // 12-17点：加 Penalty
                        merchantScores.put(t.merchantId, currentScore + t.penalty);
                    } else if ((t.hour >= 9 && t.hour <= 11) || (t.hour >= 18 && t.hour <= 21)) {
                        // 9-11点 或 18-21点：减 Penalty
                        merchantScores.put(t.merchantId, currentScore - t.penalty);
                    }
                    // 其他时间段不做处理
                }
            }
        }

        // ==========================================
        // 输出格式化
        // ==========================================
        // 将结果转为 List 并排序
        List<String> result = new ArrayList<>();
        // 获取所有商家名字并排序
        List<String> sortedMerchants = new ArrayList<>(merchantScores.keySet());
        Collections.sort(sortedMerchants);

        for (String mName : sortedMerchants) {
            result.add(mName + "," + merchantScores.get(mName));
        }

        return result;
    }

    // 简单的 Main 测试 (基于题目图片中的 Example Input)
    public static void main(String[] args) {
        List<String> transactions = Arrays.asList(
                "merchant1,1200,customer1,10",
                "merchant1,500,customer1,10",
                "merchant2,2400,customer1,15",
                "merchant1,800,customer1,16",
                "merchant1,1000,customer2,17",
                "merchant1,1400,customer1,10"
        );

        List<String> rules = Arrays.asList(
                "1000,2,8,15",
                "1400,5,3,19",
                "2300,3,17,3",
                "1800,2,9,6",
                "1000,4,8,2",
                "1200,3,11,7"
        );

        List<String> merchants = Arrays.asList(
                "merchant1,10",
                "merchant2,20"
        );

        List<String> output = calculateFraudScores(transactions, rules, merchants);
        System.out.println(output);
        // 预期输出: [merchant1,50, merchant2,60]
        // merchant2计算: Base 20 -> Trans 2400>2300 (mult 3) -> 20*3=60. Additive count=1(no). Hour count=1(no). Final 60.
    }
}