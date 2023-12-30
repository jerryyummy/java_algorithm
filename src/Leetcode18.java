import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Leetcode18 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        HashMap<Integer,List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.putIfAbsent(nums[i],new ArrayList<>());
            map.get(nums[i]).add(i);
        }

        for (int i = 0; i < nums.length - 3; i++) {
            for (int j = i + 1; j < nums.length - 2; j++) {
                for (int k = j + 1; k < nums.length - 1; k++) {
                    // Cast to long to handle large sums
                    long sum = (long) nums[i] + (long) nums[j] + (long) nums[k];
                    if (target - sum < nums[k]) break;

                    if (map.containsKey((int) (target - sum))) {
                        for (int num : map.get((int) (target - sum))) {
                            if (num <= k) continue;
                            else {
                                res.add(Arrays.asList(nums[i], nums[j], nums[k], nums[num]));
                                break;
                            }
                        }
                    }
                    while (k + 1 < nums.length - 1 && nums[k + 1] == nums[k]) k++;
                }
                while (j + 1 < nums.length - 2 && nums[j + 1] == nums[j]) j++;
            }
            while (i + 1 < nums.length - 3 && nums[i + 1] == nums[i]) i++;
        }
        return res;
    }
}
