/*
A permutation of an array of integers is an arrangement of its members into a sequence or linear order.

For example, for arr = [1,2,3], the following are all the permutations of arr: [1,2,3], [1,3,2], [2, 1, 3], [2, 3, 1], [3,1,2], [3,2,1].
The next permutation of an array of integers is the next lexicographically greater permutation of its integer. More formally, if all the permutations of the array are sorted in one container according to their lexicographical order, then the next permutation of that array is the permutation that follows it in the sorted container. If such arrangement is not possible, the array must be rearranged as the lowest possible order (i.e., sorted in ascending order).

For example, the next permutation of arr = [1,2,3] is [1,3,2].
Similarly, the next permutation of arr = [2,3,1] is [3,1,2].
While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1] does not have a lexicographical larger rearrangement.
Given an array of integers nums, find the next permutation of nums.

The replacement must be in place and use only constant extra memory.
 */
public class NextPermutation {
    public void nextPermutation(int[] nums) {

        int len = nums.length;
        int i1 = -1;
        int i2 = -1;
        // finding the index i1
        for(int i = len - 2; i >= 0; i--){
            if(nums[i] < nums[i + 1]){
                i1 = i;
                break;//从后往前找第一个小于后一个数的数
            }
        }
        if(i1 == -1){
            reverse(nums, 0, len - 1);//如果没找到，说明是逆序排列
        } else {
            //找到一个第一个的大于上一个找到的数的数
            for(int i = len - 1; i >= 0; i--){
                if(nums[i] > nums[i1]){
                    i2 = i;
                    break;
                }
            }

            swap(nums, i1, i2);
            reverse(nums, i1 + 1, len - 1);
        }
    }

    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] A, int i, int j) {
        while(i < j) swap(A, i++, j--);
    }
}
