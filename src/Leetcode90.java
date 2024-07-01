import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Leetcode90 {
  public List<List<Integer>> subsetsWithDup(int[] nums){
    List<List<Integer>> res = new ArrayList<>();
    res.add(new ArrayList<>());
    int n = nums.length;
    Arrays.sort(nums);

    int maxNumberOfSubsets = (int) Math.pow(2, n);
    Set<String> seen = new HashSet<>();
    for (int subsetIndex = 0; subsetIndex < maxNumberOfSubsets; subsetIndex++) {
      // Append subset corresponding to that bitmask.
      List<Integer> currentSubset = new ArrayList();
      StringBuilder hashcode = new StringBuilder();
      for (int j = 0; j < n; j++) {
        // Generate the bitmask
        int mask = 1 << j;
        int isSet = mask & subsetIndex;
        if (isSet != 0) {
          currentSubset.add(nums[j]);
          // Generate the hashcode by creating a comma separated string of numbers in the currentSubset.
          hashcode.append(nums[j]).append(",");
        }
      }
      if (!seen.contains(hashcode.toString())) {
        seen.add(hashcode.toString());
        res.add(currentSubset);
      }

    }
    return res;
  }
}
