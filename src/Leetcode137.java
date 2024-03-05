class Leetcode137 {
  public int singleNumber(int[] nums) {

    // Loner
    int loner = 0;
    for (int shift = 0; shift < 32; shift++) {//分别计算每一位上1出现的次数，然后根据这个次数来确定 loner 在该位上的值
      int bitSum = 0;

      // For this bit, iterate over all integers
      for (int num : nums) {

        // Compute the bit of num, and add it to bitSum
        bitSum += (num >> shift) & 1;
      }

      //bitSum % 3 的结果要么是0（表示 loner 在这一位上为0），要么是1（表示 loner 在这一位上为1）
      int lonerBit = bitSum % 3;
      loner = loner | (lonerBit << shift);
    }
    return loner;
  }
}
