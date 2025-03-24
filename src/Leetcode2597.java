import java.util.*;

public class Leetcode2597 {
//    int res = 0;
//
//    public int beautifulSubsets(int[] nums, int k) {
//        int n = nums.length;
//        for (int i = 1; i < Math.pow(2,n); i++) {
//            backtrack(nums,k,i, new HashSet<>());
//        }
//        return res;
//    }
//
//    public void backtrack(int[] nums, int k, int state, Set<Integer> set) {
//        int n = nums.length;
//        boolean flag = true;
//        for (int i = 0; i < n; i++) {
//            boolean use = (state&1)==1;
//            state = state>>1;
//            if (use) {
//                if(!set.contains(nums[i]-k) && !set.contains(nums[i]+k)){
//                    set.add(nums[i]);
//                }else{
//                    flag = false;
//                }
//            }
//        }
//        if(flag){
//            res++;
//        }
//    }

    public int beautifulSubsets(int[] nums, int k) {
        int totalCount = 1;

        Map<Integer, Map<Integer, Integer>> freqMap = new TreeMap<>();

        for (int num : nums) {
            int remainder = num % k;
            freqMap
                    .computeIfAbsent(remainder, x -> new TreeMap<>())
                    .merge(num, 1, Integer::sum);
        }

        for (Map.Entry<Integer,Map<Integer, Integer>> entry : freqMap.entrySet()) {
            int n = entry.getValue().size();
            List<Map.Entry<Integer, Integer>> subsets = new ArrayList<>(
                    entry.getValue().entrySet()
            );

            int[] counts = new int[n + 1];

            counts[n] = 1;

            for (int i = n - 1; i >= 0; i--) {
                int skip = counts[i + 1];

                int take = (1 << subsets.get(i).getValue()) - 1;

                if (
                        i + 1 < n &&
                                subsets.get(i + 1).getKey() - subsets.get(i).getKey() == k
                ) {
                    take *= counts[i + 2];
                } else {
                    take *= counts[i + 1];
                }

                counts[i] = skip + take;
            }

            totalCount *= counts[0];
        }

        return totalCount - 1;
    }
}
