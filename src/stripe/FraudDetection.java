package stripe;

import java.util.*;

public class FraudDetection {

    public static List<String> calculateFraudScores(List<String> transactionsList,
                                                    List<String> rulesList,
                                                    List<String> merchantsList) {
        // 1. 初始化商户分数和商户列表
        Map<String, Integer> score = new HashMap<>();
        List<String> merchantNames = new ArrayList<>();

        for (String s : merchantsList) {
            String[] parts = s.split(",", 2);
            String m = parts[0].trim();
            int baseScore = Integer.parseInt(parts[1].trim());
            score.put(m, baseScore);
            merchantNames.add(m);
        }

        // 2. 解析交易和规则数据
        List<TransactionData> txs = new ArrayList<>();
        for (int i = 0; i < transactionsList.size(); i++) {
            String[] tParts = transactionsList.get(i).split(",");
            String[] rParts = rulesList.get(i).split(",");

            txs.add(new TransactionData(
                    tParts[0].trim(),                     // merchant
                    Integer.parseInt(tParts[1].trim()),   // amount
                    tParts[2].trim(),                     // card
                    Integer.parseInt(tParts[3].trim()),   // hour
                    Integer.parseInt(rParts[0].trim()),   // minAmt
                    Integer.parseInt(rParts[1].trim()),   // multiplier
                    Integer.parseInt(rParts[2].trim()),   // addition
                    Integer.parseInt(rParts[3].trim())    // penalty
            ));
        }

        // 3. 第一轮：乘法规则 (Multiplication)
        // 逻辑：如果交易金额 > minAmt，基础分乘以倍率
        for (TransactionData tx : txs) {
            if (score.containsKey(tx.merchant) && tx.amount > tx.minAmt) {
                score.put(tx.merchant, score.get(tx.merchant) * tx.multiplier);
            }
        }

        // 4. 第二轮：商户+卡号 累加规则 (Merchant-Card pair)
        // 逻辑：同一商户同一卡号出现 >= 3 次，增加分值
        Map<String, Integer> cntPair = new HashMap<>();
        Map<String, Integer> sumAdd = new HashMap<>();
        for (TransactionData tx : txs) {
            if (!score.containsKey(tx.merchant)) continue;
            String key = tx.merchant + "#" + tx.card;
            cntPair.put(key, cntPair.getOrDefault(key, 0) + 1);
            sumAdd.put(key, sumAdd.getOrDefault(key, 0) + tx.addition);
        }
        for (Map.Entry<String, Integer> entry : cntPair.entrySet()) {
            if (entry.getValue() >= 3) {
                String m = entry.getKey().split("#")[0];
                score.put(m, score.getOrDefault(m, 0) + sumAdd.get(entry.getKey()));
            }
        }

        // 5. 第三轮：小时惩罚规则 (Hourly Penalty)
        // 逻辑：同一小时内出现 >= 3 次，根据时间段正向或负向加分
        Map<String, Integer> cntHour = new HashMap<>();
        Map<String, Integer> sumPen = new HashMap<>();
        Map<String, Integer> hourByKey = new HashMap<>();
        for (TransactionData tx : txs) {
            if (!score.containsKey(tx.merchant)) continue;
            String key = tx.merchant + "#" + tx.card + "#" + tx.hour;
            cntHour.put(key, cntHour.getOrDefault(key, 0) + 1);
            sumPen.put(key, sumPen.getOrDefault(key, 0) + tx.penalty);
            hourByKey.putIfAbsent(key, tx.hour);
        }
        for (Map.Entry<String, Integer> entry : cntHour.entrySet()) {
            if (entry.getValue() >= 3) {
                int h = hourByKey.get(entry.getKey());
                int sign = 0;
                if (h >= 12 && h <= 17) {
                    sign = 1;
                } else if ((h >= 9 && h <= 11) || (h >= 18 && h <= 21)) {
                    sign = -1;
                }

                if (sign != 0) {
                    String m = entry.getKey().split("#")[0];
                    score.put(m, score.getOrDefault(m, 0) + (sign * sumPen.get(entry.getKey())));
                }
            }
        }

        // 6. 排序并生成结果
        Collections.sort(merchantNames);
        List<String> result = new ArrayList<>();
        for (String m : merchantNames) {
            result.add(m + "," + score.getOrDefault(m, 0));
        }

        return result;
    }

    private static class TransactionData {
        String merchant, card;
        int amount, hour, minAmt, multiplier, addition, penalty;

        TransactionData(String merchant, int amount, String card, int hour,
                        int minAmt, int multiplier, int addition, int penalty) {
            this.merchant = merchant;
            this.amount = amount;
            this.card = card;
            this.hour = hour;
            this.minAmt = minAmt;
            this.multiplier = multiplier;
            this.addition = addition;
            this.penalty = penalty;
        }
    }

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
    }
}