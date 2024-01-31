public class Leetcode1802 {
  private long getSum(int index, int value, int n) {//计算以特定值 value 在索引 index 处，长度为 n 的数组可能达到的最大和
    long count = 0;

    if (value > index) {//计算索引 index 前面部分的和
      count += (long)(value + value - index) * (index + 1) / 2;
    } else {
      count += (long)(value + 1) * value / 2 + index - value + 1;
    };

    if (value >= n - index) {//元素值大于或等于其到数组末尾的距离
      count += (long)(value + value - n + 1 + index) * (n - index) / 2;
    } else {
      count += (long)(value + 1) * value / 2 + n - index - value;
    }

    return count - value;

  }
  public int maxValue(int n, int index, int maxSum) {
    int left = 1, right = maxSum;
    while (left < right) {
      int mid = (left + right + 1) / 2;
      if (getSum(index, mid, n) <= maxSum) {
        left = mid;
      } else {
        right = mid - 1;
      }
    }

    return left;
  }

}
