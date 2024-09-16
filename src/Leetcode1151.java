public class Leetcode1151 {
  public int minSwaps(int[] data) {
    int ones = 0;

    // Count the total number of ones in the array
    for (int num : data) {
      if (num == 1) {
        ones++;
      }
    }

    // Initialize the count of zeros in the first window of size 'ones'
    int operation = 0;
    for (int i = 0; i < ones; i++) {
      if (data[i] == 0) {
        operation++;
      }
    }

    // Sliding window to find the minimum number of swaps needed
    int minSwaps = operation;
    for (int i = 1, j = ones; j < data.length; i++, j++) {
      // If we are sliding past a zero at the start of the window, reduce count
      if (data[i - 1] == 0) {
        operation--;
      }
      // If we are including a new zero at the end of the window, increase count
      if (data[j] == 0) {
        operation++;
      }
      // Update the minimum number of swaps required
      minSwaps = Math.min(minSwaps, operation);
    }

    return minSwaps;
  }
}
