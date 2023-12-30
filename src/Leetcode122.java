public class Leetcode122 {
    public int maxProfit(int[] prices) {
        int sum = 0;
        int cur = Integer.MAX_VALUE;
        for (int price:prices){
            cur = Math.min(cur,price);
            if (price > cur){
                sum+=(price-cur);
                cur = price;
            }
        }
        return sum;
    }
}
