import java.util.Arrays;

public class Leetcode2300 {
  public int[] successfulPairs(int[] spells, int[] potions, long success) {
    int n = spells.length;
    int[] res = new int[n];

    Arrays.sort(potions);

    for (int i = 0; i < n; i++) {
      int left = 0, right = potions.length - 1;
      while (left <= right) {
        int mid = (right + left) / 2;
        if ((long)spells[i]*(long)potions[mid] >= success){
          right = mid-1;
        }else{
          left = mid + 1;
        }
      }
      res[i] = potions.length-left;
    }

    return res;
  }
}
