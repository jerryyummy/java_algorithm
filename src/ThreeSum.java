/*
Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.

Notice that the solution set must not contain duplicate triplets.
 */
import java.util.*;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
//        Set<List<Integer>> resultSet = new HashSet();
//        Set<Integer> duplicatedSet = new HashSet<>();
//        Map<Integer, Integer> map = new HashMap<>();
//
//        for(int i = 0; i < nums.length - 2; i++) {
//            if (!duplicatedSet.add(nums[i])) continue;
//
//            for (int j = i + 1; j < nums.length; j++) {
//                int value = 0 - nums[i] - nums[j];
//                if (map.containsKey(value) && map.get(value) == i) {
//                    List<Integer> list = new ArrayList<>(Arrays.asList(nums[i], nums[j], value));
//                    Collections.sort(list);
//                    resultSet.add(list);
//                }
//                map.put(nums[j], i);
//            }
//        }
//        return new ArrayList<>(resultSet) ;

        Set<List<Integer>> resultSet = new HashSet();
        Arrays.sort(nums);
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++) map.put(nums[i], i);

        for(int i = 0; i < nums.length - 2 && nums[i] <= 0;){//如果第一个数都是正数，无法得到结果
            for (int j = i + 1; j < nums.length-1 && nums[i] + nums[j] <= 0;) {//前两个数不能是正数，不然接下来一个数肯定是正整数
                int value = 0 - nums[i] - nums[j];//target number
                if (value < nums[j]) break;//如果value小于当前索引值，无法找到，因为之后的数更大
                if (map.containsKey(value) && map.get(value) > j)
                    resultSet.add(Arrays.asList(nums[i], nums[j], value));
                j++;
                while(j < nums.length-1 && nums[j] == nums[j - 1]) j++;//找到下一个更大的数
            }
            i++;
            while(i < nums.length - 2 && nums[i] == nums[i - 1]) i++;
        }
        return new ArrayList<>(resultSet) ;
    }

}
