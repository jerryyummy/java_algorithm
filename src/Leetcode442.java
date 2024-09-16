import java.util.ArrayList;
import java.util.List;

public class Leetcode442 {
  public List<Integer> findDuplicates(int[] nums) {
    int n = nums.length;

    int i = 0;
    while (i < n) {
      int correctIdx = nums[i] - 1;
      if (nums[i] != nums[correctIdx]) {
        swap(nums, i, correctIdx);
      } else {
        i++;
      }
    }

    // Any elements not at the index that corresponds to their value are duplicates
    List<Integer> duplicates = new ArrayList<>();
    for (i = 0; i < n; i++) {
      if (nums[i] != i + 1) {
        duplicates.add(nums[i]);
      }
    }

    return duplicates;
  }

  // Swaps two elements in nums
  private void swap(int[] nums, int index1, int index2) {
    int temp = nums[index1];
    nums[index1] = nums[index2];
    nums[index2] = temp;
  }
}
