import java.util.Arrays;

public class Leetcode475 {
  public int findRadius(int[] houses, int[] heaters) {
    int left = 0, right = (int) 1e9;
    Arrays.sort(houses);
    Arrays.sort(heaters);
    while (left <= right) {
      int mid = (left + right) / 2;
      if (isPossible(houses, heaters, mid)) {
        right = mid - 1;
      }else{
        left = mid + 1;
      }
    }
    return left;
  }

  public boolean isPossible(int[] houses, int[] heaters, int r) {
    int i = 0, j = 0;
    while (i < houses.length){
      if (houses[i] < heaters[j] - r){
        return false;
      }else if(houses[i] > heaters[j] + r){
        if(++j == heaters.length){
          return false;
        }
        i--;
      }
      i++;
    }
    return true;
  }
}
