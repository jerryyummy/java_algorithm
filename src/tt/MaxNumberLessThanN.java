package tt;

import java.util.Arrays;

public class MaxNumberLessThanN {
  public static void main(String[] args) {
    int[] arr = {1, 2, 4, 5,7,3};
    int n = 7534615;
    System.out.println(findMaxLessThanN(arr, n));
  }

  public static int findMaxLessThanN(int[] arr, int n) {
    Arrays.sort(arr);
    String targetStr = String.valueOf(n);
    StringBuilder result = new StringBuilder();

    boolean flag = false;
    for (int i = 0; i < targetStr.length(); i++) {
      int digit = Character.getNumericValue(targetStr.charAt(i));
      if (flag) {
        result.append(arr[arr.length - 1]);
        continue;
      }
      int idx = binarySearch(arr, digit);
      // 如果找不到合适的数字替换当前位，直接返回结果
      if (idx == -1) return -1;
      result.append(arr[idx]);  // 选择比当前位小的最大数字
      if (Integer.parseInt(result.toString()) < Integer.parseInt(targetStr.substring(0,i+1))) flag = true;
    }

    // 如果结果为空或初始条件不满足，返回-1
    return Integer.parseInt(result.toString());
  }

  private static int binarySearch(int[] arr, int n) {
    int low = 0;
    int high = arr.length - 1;
    while (low <= high) {
      int mid = (low + high) / 2;
      if (arr[mid] > n) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return high;
  }
}