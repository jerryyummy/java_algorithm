package amazon;

import java.util.Arrays;

public class BoxBalancer {
    public long findMinimumOperations(int[] boxes) {
        Arrays.sort(boxes);
        int n = boxes.length;
        int operations = 0;

        int left = 0, right = n - 1;

        while (boxes[right] - boxes[left] > 1) {
            boxes[right]--;
            boxes[left]++;
            operations++;

            if (boxes[left] > boxes[left + 1]) {
                left++;
            }
            if (boxes[right] < boxes[right - 1]) {
                right--;
            }
        }

        return operations;
    }

    public static void main(String[] args) {
        BoxBalancer solution = new BoxBalancer();
        int[] piles = {5,5,8,7};
        System.out.println(solution.findMinimumOperations(piles));
    }
}
