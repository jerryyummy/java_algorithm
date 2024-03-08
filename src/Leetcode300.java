public class Leetcode300 {
  public int lengthOfLIS(int[] nums) {
    int[] tails = new int[nums.length];//存储长度为 i + 1 的最小子序列的最后一个元素
    int size = 0;
    for (int x : nums) {
      int i = 0, j = size;
      while (i != j) {//使得 tails[m] 是第一个大于或等于 x 的元素。如果 tails[m] < x，表示 x 可以潜在地扩展到 m 形成的子序列，所以查找在后半部继续。否则，查找在前半部继续
        int m = (i + j) / 2;
        if (tails[m] < x)
          i = m + 1;
        else
          j = m;
      }
      tails[i] = x;
      if (i == size) ++size;
    }
    return size;
  }
}
