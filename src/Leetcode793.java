public class Leetcode793 {
  private int k2;

  // 辅助函数，用来判断mid阶乘中的零尾数是否小于等于k2
  private boolean check(long mid) {
    int count = 0;
    while (mid > 0) {
      count += mid / 5;
      mid /= 5;
    }
    return count <= k2;
  }

  // 主函数，返回恰好有k个零尾数的数字的个数
  public int preimageSizeFZF(int k) {
    long l = 0;
    long r = 5 * 1000000000L;  // 5 * 10^9
    long ans = 0;
    k2 = k;  // 把k的值赋给类的成员变量k2

    // 二分查找
    while (l <= r) {
      long mid = (l + r) / 2;
      if (check(mid)) {
        l = mid + 1;
        ans = mid;
      } else {
        r = mid - 1;
      }
    }

    // 检查找到的数字是否有k个尾随零
    int count = 0;
    while (ans > 0) {
      count += ans / 5;
      ans /= 5;
    }
    return 5 * (count == k ? 1 : 0);  // 如果有k个零，返回5，否则返回0
  }
}
