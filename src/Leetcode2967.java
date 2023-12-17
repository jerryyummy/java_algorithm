import java.util.Arrays;

public class Leetcode2967 {
    // Method to check if a number is a palindrome
    private boolean isPalindrome(int num) {
        String s = Integer.toString(num);
        int i = 0, j = s.length() - 1;
        while (i <= j) {
            if (s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }

    // Method to calculate the minimum cost
    public long minimumCost(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        long median = nums[n / 2];

        long n1 = median, n2 = median;
        while (!isPalindrome((int)n1)) n1++;
        while (!isPalindrome((int)n2)) n2--;

        long c1 = 0, c2 = 0;
        for (int num : nums) {
            c1 += Math.abs(num - n1);
            c2 += Math.abs(num - n2);
        }
        return Math.min(c1, c2);
    }

}
