import java.util.PriorityQueue;
import java.util.Random;

public class FindKthLargest {
    public int findKthLargest(int[] nums, int k) {
//        PriorityQueue<Integer> pq =new PriorityQueue<>((a, b) -> b - a);
//        for (int i = 0; i < nums.length; i++) {
//            pq.offer(nums[i]);
//        }
//        while(k>0){
//            pq.poll();
//            k--;
//        }
//        return pq.peek();
        return quickSelect(nums, 0, nums.length-1, nums.length-k);
    }

    private int quickSelect(int[] nums, int left, int right, int k) {
        if (left == right) return nums[left];

        int pIndex = new Random().nextInt(right - left + 1) + left;
        pIndex = partition(nums, left, right, pIndex);

        if (pIndex == k) return nums[k];
        else if (pIndex < k) return quickSelect(nums, pIndex+1, right, k);
        return quickSelect(nums, left, pIndex-1, k);
    }

    private int partition(int[] nums, int left, int right, int pIndex) {
        int pivot = nums[pIndex];
        swap(nums, pIndex, right);
        pIndex = left;

        for (int i=left; i<=right; i++)
            if (nums[i] <= pivot)
                swap(nums, i, pIndex++);

        return pIndex - 1;
    }

    private void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
}

