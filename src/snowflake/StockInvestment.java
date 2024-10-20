package snowflake;

public class StockInvestment {

  // Function to calculate maximum profit
  public static int selectStock(int saving, int[] currentValue, int[] futureValue) {
    int n = currentValue.length;
    int[] dp = new int[saving + 1];

    // 遍历每个股票
    for (int i = 0; i < n; i++) {
      int profit = futureValue[i] - currentValue[i];

      // 动态规划逆序遍历，防止重复选择同一股票
      for (int w = saving; w >= currentValue[i]; w--) {
        dp[w] = Math.max(dp[w], dp[w - currentValue[i]] + profit);
      }
    }

    // 返回最大可能的利润
    return dp[saving];
  }

  public static void main(String[] args) {
    // 测试用例
    int saving = 250;
    int[] currentValue = {175, 133, 109, 210, 97};
    int[] futureValue = {200, 125, 128, 228, 133};

    int result = selectStock(saving, currentValue, futureValue);
    System.out.println("最大利润: " + result);  // 输出最大利润
  }
}
