package snowflake;

public class CustomSortedArray {

  public static int moves(int[] arr) {
    int left = 0;
    int right = arr.length - 1;
    int swaps = 0;

    // 使用双指针从左右两侧遍历
    while (left < right) {
      // 从左侧找到第一个奇数
      while (left < right && arr[left] % 2 == 0) {
        left++;
      }

      // 从右侧找到第一个偶数
      while (left < right && arr[right] % 2 != 0) {
        right--;
      }

      // 交换奇数和偶数的位置
      if (left < right) {
        // 交换 arr[left] 和 arr[right]
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;

        // 计数一次交换
        swaps++;

        // 移动指针
        left++;
        right--;
      }
    }

    return swaps;
  }

  public static void main(String[] args) {
    // 测试用例
    int[] arr = {6, 3, 4, 5};
    int result = moves(arr);
    System.out.println("最少交换次数: " + result);
  }
}
