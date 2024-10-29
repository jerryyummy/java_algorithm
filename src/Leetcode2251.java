import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leetcode2251 {
  public int[] fullBloomFlowers(int[][] flowers, int[] people) {
    int[] start = new int[flowers.length];
    int[] end = new int[flowers.length];
    for (int i = 0; i < flowers.length; i++) {
      start[i] = flowers[i][0];
      end[i] = flowers[i][1];
    }
    Arrays.sort(start);
    Arrays.sort(end);

    ArrayList<Integer> res = new ArrayList<>();
    for (int person : people) {
      int index1 = binarySearch(start, person + 1); // 查找比 person 大的第一个位置
      int index2 = binarySearch(end, person);       // 查找 <= person 的最后一个位置
      res.add(index1 - index2);
    }
    return res.stream().mapToInt(Integer::intValue).toArray();
  }

  public int binarySearch(int[] nums, int target) {
    int low = 0;
    int high = nums.length - 1;
    while (low <= high) {
      int mid = (low + high) >>> 1;
      if (nums[mid] < target) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    return low;
  }
}
