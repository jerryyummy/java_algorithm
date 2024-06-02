package snowflake;

import java.util.*;

public class SpecialStringPermutation {
  public static void main(String[] args) {
    List<Integer> res = countMinimumCharacters("064819848398", Arrays.asList("088", "364", "07"));
    System.out.println(res); // 输出示例
  }

  public static List<Integer> countMinimumCharacters(String s, List<String> arr) {
    List<Integer> result = new ArrayList<>();
    int[][] digitCountUpToIndex = new int[s.length()][10];

    // Counting the occurrences of each digit up to each index
    for (int i = 0; i < s.length(); i++) {
      if (i > 0) {
        System.arraycopy(digitCountUpToIndex[i - 1], 0, digitCountUpToIndex[i], 0, 10);
      }
      digitCountUpToIndex[i][s.charAt(i) - '0']++;
    }

    for (String target : arr) {
      result.add(findMinimumLength(digitCountUpToIndex, target));
    }

    return result;
  }

  private static int findMinimumLength(int[][] digitCountUpToIndex, String target) {
    int[] targetCount = new int[10];
    for (char c : target.toCharArray()) {
      targetCount[c - '0']++;
    }

    int left = 0, right = digitCountUpToIndex.length - 1, minLength = -1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (hasAllDigits(digitCountUpToIndex[mid], targetCount)) {
        minLength = mid + 1; // Found a valid length, try to find a smaller one
        right = mid - 1;
      } else {
        left = mid + 1; // Not enough digits, look further
      }
    }

    return minLength;
  }

  private static boolean hasAllDigits(int[] sCount, int[] targetCount) {
    for (int i = 0; i < 10; i++) {
      if (sCount[i] < targetCount[i]) {
        return false;
      }
    }
    return true;
  }
}

