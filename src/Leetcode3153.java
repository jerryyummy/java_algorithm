public class Leetcode3153 {
  public long sumDigitDifferences(int[] nums) {
    int n = nums.length;
    if (n == 0) return 0;

    int numDigits = String.valueOf(nums[0]).length();//获取有多少位数
    long res = 0;

    int[][] digitCount = new int[numDigits][10];

    // Count digit occurrences at each position
    for (int num : nums) {
      for (int pos = 0; pos < numDigits; pos++) {
        int digit = (num / (int) Math.pow(10, numDigits - pos - 1)) % 10;//获取当前数字的当前位数
        digitCount[pos][digit]++;
      }
    }

    // Calculate the digit differences
    for (int pos = 0; pos < numDigits; pos++) {
      for (int digit1 = 0; digit1 < 10; digit1++) {
        for (int digit2 = digit1 + 1; digit2 < 10; digit2++) {
          int count1 = digitCount[pos][digit1];
          int count2 = digitCount[pos][digit2];
          res += (long) count1 * count2;
        }
      }
    }

    return res;
  }
}
