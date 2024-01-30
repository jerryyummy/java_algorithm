package snowflake;

import java.util.Arrays;

public class EfficientCost {

  // 动态规划函数
  public static int calculateCost(int[] arr, int threshold) {
    int n = arr.length;
    int[] cost = new int[n + 1]; // DP数组，cost[i]表示数组前i个元素的最小分割成本
    Arrays.fill(cost, Integer.MAX_VALUE); // 初始化成本为最大整数值
    cost[0] = 0; // 边界条件，没有元素的成本为0

    for (int i = 0; i < n; i++) {
      int maxVal = 0; // 初始化当前子数组的最大值
      for (int j = i; j < Math.min(n, i + threshold); j++) { // 遍历所有可能的分割位置
        maxVal = Math.max(maxVal, arr[j]); // 更新子数组的最大值
        if (cost[i] != Integer.MAX_VALUE) { // 如果当前成本不是最大值，则计算新的成本
          cost[j + 1] = Math.min(cost[j + 1], cost[i] + maxVal); // 更新DP数组
        }
      }
    }

    return cost[n]; // 返回整个数组的最小分割成本
  }

  public static void main(String[] args) {
    int[] arr = {1, 2, 3, 5, 4, 6};
    int threshold = 3;
    int result = calculateCost(arr, threshold);
    System.out.println(result); // 应输出最小成本
  }
}

