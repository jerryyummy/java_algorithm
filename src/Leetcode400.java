public class Leetcode400 {
  public int findNthDigit(int n) {
    long base = 9, digits = 1;
    // 首先确定n落在哪个区间
    while (n - base * digits > 0) {
      n -= base * digits;
      base *= 10;
      digits++;
    }

    // 确定n所在的数字
    long index = n - 1; // 将索引调整为从0开始
    long start = (long)Math.pow(10, digits - 1); // 当前区间的起始数字
    long num = start + index / digits; // 确定n所在的完整数字

    // 返回n指定位置的数字
    return Long.toString(num).charAt((int)(index % digits)) - '0';
  }

}
