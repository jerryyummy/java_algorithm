package stripe;

import java.util.*;

public class StripeCardValidator {

    private static final String VISA = "VISA";
    private static final String MASTERCARD = "MASTERCARD";
    private static final String AMEX = "AMEX";
    private static final String INVALID_CHECKSUM = "INVALID_CHECKSUM";
    private static final String UNKNOWN_NETWORK = "UNKNOWN_NETWORK";

    public static void main(String[] args) {
        // --- Part 1 ---
        System.out.println("--- Part 1 ---");
        System.out.println(validatePart1("4532015112830366")); // Expected: VISA
        System.out.println(validatePart1("4242424242424243")); // Expected: INVALID_CHECKSUM

        // --- Part 2 ---
        System.out.println("\n--- Part 2 ---");
        System.out.println(validatePart2("5482334509943"));    // Expected: UNKNOWN_NETWORK
        System.out.println(validatePart2("4425233430109994")); // Note: Checksum invalid mathematically
        System.out.println(validatePart2("562523343010901"));  // Expected: UNKNOWN_NETWORK

        // --- Part 3 ---
        System.out.println("\n--- Part 3 ---");
        // Test case 1
        solvePart3("4242424242424*42");
        // Test case 2
        solvePart3("3*8282246310005");
        // Test case 3 (Multiple networks possible)
        solvePart3("**2424242424242");

        // --- Part 4 ---
        System.out.println("\n--- Part 4 ---");
        solvePart4("4344555566660004?");
    }

    /**
     * Part 1: Basic Visa Validation
     */
    public static String validatePart1(String card) {
        if (!card.startsWith("4") || card.length() != 16) {
            return INVALID_CHECKSUM;
        }
        return isLuhnValid(card) ? VISA : INVALID_CHECKSUM;
    }

    /**
     * Part 2: Multi-Network Validation
     */
    public static String validatePart2(String card) {
        String network = identifyNetwork(card);

        if (network.equals(UNKNOWN_NETWORK)) {
            return UNKNOWN_NETWORK;
        }

        if (!isLuhnValid(card)) {
            return INVALID_CHECKSUM;
        }

        return network;
    }

    /**
     * Part 3: Redacted Cards
     */
    public static void solvePart3(String redactedCard) {
        Map<String, Integer> counts = new HashMap<>();
        generateAndCheck(redactedCard.toCharArray(), 0, counts);

        List<String> sortedNetworks = new ArrayList<>(counts.keySet());
        Collections.sort(sortedNetworks); // Alphabetical sort: AMEX, MASTERCARD, VISA

        for (String net : sortedNetworks) {
            System.out.println(net + "," + counts.get(net));
        }
    }

    private static void generateAndCheck(char[] chars, int index, Map<String, Integer> counts) {
        if (index == chars.length) {
            String candidate = new String(chars);
            // 这里逻辑复用 Part 2：先看是否是已知网络，再校验 Luhn
            String net = identifyNetwork(candidate);
            if (!net.equals(UNKNOWN_NETWORK) && isLuhnValid(candidate)) {
                counts.put(net, counts.getOrDefault(net, 0) + 1);
            }
            return;
        }

        if (chars[index] == '*') {
            for (char d = '0'; d <= '9'; d++) {
                chars[index] = d;
                generateAndCheck(chars, index + 1, counts);
            }
            chars[index] = '*'; // Backtrack
        } else {
            generateAndCheck(chars, index + 1, counts);
        }
    }

    /**
     * Part 4: Corrupted Cards
     */
    public static void solvePart4(String input) {
        // 移除末尾的 '?'
        String corrupted = input.substring(0, input.length() - 1);
        Set<String> validCards = new HashSet<>();
        char[] chars = corrupted.toCharArray();

        // 1. 尝试修复：单个数字改变
        for (int i = 0; i < chars.length; i++) {
            char original = chars[i];
            for (char d = '0'; d <= '9'; d++) {
                if (d == original) continue; // "exactly one error" -> 结果必须不同

                chars[i] = d;
                String candidate = new String(chars);
                if (isValidCard(candidate)) {
                    validCards.add(candidate);
                }
            }
            chars[i] = original; // Backtrack
        }

        // 2. 尝试修复：相邻数字交换
        for (int i = 0; i < chars.length - 1; i++) {
            if (chars[i] == chars[i+1]) continue; // 交换相同数字不是有效错误

            // Swap
            char temp = chars[i];
            chars[i] = chars[i+1];
            chars[i+1] = temp;

            String candidate = new String(chars);
            if (isValidCard(candidate)) {
                validCards.add(candidate);
            }

            // Swap back
            temp = chars[i];
            chars[i] = chars[i+1];
            chars[i+1] = temp;
        }

        // 结果排序：数值升序
        List<String> sortedResults = new ArrayList<>(validCards);
        Collections.sort(sortedResults, (a, b) -> {
            if (a.length() != b.length()) return a.length() - b.length();
            return a.compareTo(b);
        });

        for (String card : sortedResults) {
            System.out.println(card + "," + identifyNetwork(card));
        }
    }

    // --- Helper Methods ---

    private static boolean isValidCard(String card) {
        String net = identifyNetwork(card);
        if (net.equals(UNKNOWN_NETWORK)) return false;
        return isLuhnValid(card);
    }

    private static String identifyNetwork(String card) {
        int len = card.length();

        if (len == 16 && card.startsWith("4")) {
            return VISA;
        }

        if (len == 16 && card.length() >= 2) {
            try {
                int prefix = Integer.parseInt(card.substring(0, 2));
                if (prefix >= 51 && prefix <= 55) {
                    return MASTERCARD;
                }
            } catch (NumberFormatException e) {
                return UNKNOWN_NETWORK;
            }
        }

        if (len == 15 && (card.startsWith("34") || card.startsWith("37"))) {
            return AMEX;
        }

        return UNKNOWN_NETWORK;
    }

    private static boolean isLuhnValid(String card) {
        int sum = 0;
        int len = card.length();

        // 从右往左遍历
        // len-1 是校验位，不乘
        // len-2 乘
        for (int i = len - 1; i >= 0; i--) {
            int digit = card.charAt(i) - '0';
            boolean shouldDouble = ((len - i) % 2 == 0);

            if (shouldDouble) {
                digit *= 2;
                if (digit > 9) digit -= 9;
            }

            sum += digit;
        }

        return sum % 10 == 0;
    }
}