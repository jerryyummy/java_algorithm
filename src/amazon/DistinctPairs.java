package amazon;

import java.util.*;

public class DistinctPairs {
    public static int getDistinctPairs(int[] stocksProfit, int target) {
        int count = 0;
        Set<List<Integer>> seen = new HashSet<>();
        Map<Integer, Integer> hashMap = new HashMap<>();

        for (int profit : stocksProfit) {
            int diff = target - profit;

            if (hashMap.containsKey(profit)) {
                List<Integer> pair = Arrays.asList(hashMap.get(profit), profit);
                pair.sort(null); // 确保顺序唯一
                if (!seen.contains(pair)) {
                    seen.add(pair);
                    count++;
                }
            } else {
                hashMap.put(diff, profit);
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[] stocksProfit = {5, 7, 9, 13, 11, 6, 6, 3, 3};
        int target = 12;
        System.out.println(getDistinctPairs(stocksProfit, target));
    }
}
