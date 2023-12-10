import java.util.HashSet;

public class Leetcode2956 {
    public int[] findIntersectionValues(int[] nums1, int[] nums2) {
        int[] res = new int[2];
        HashSet<Integer> set1 = new HashSet<>(), set2 = new HashSet<>();
        for (int number:nums1){
            set1.add(number);
        }
        for (int number:nums2){
            set2.add(number);
        }
        int count = 0;
        for (int i = 0; i < nums1.length; i++) {
            if (set2.contains(nums1[i])) count++;
        }
        res[0] = count;
        count = 0;
        for (int i = 0; i < nums2.length; i++) {
            if (set1.contains(nums2[i])) count++;
        }
        res[1] = count;
        return res;
    }
}
