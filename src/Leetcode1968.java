public class Leetcode1968 {
  public int[] rearrangeArray(int[] nums) {
    int n = nums.length;

    for (int i = 0; i < n; i++) {
      int randomIndex = i + (int) (Math.random() * (n - i));
      int temp = nums[randomIndex];
      nums[randomIndex] = nums[i];
      nums[i] = temp;
    }

    for (int i = 1; i < n - 1; i++) {
      if ((nums[i - 1] + nums[i + 1]) / 2 == nums[i]) {
        int temp = nums[i];
        nums[i] = nums[i + 1];
        nums[i + 1] = temp;
      }
    }

    return nums;
  }
}
