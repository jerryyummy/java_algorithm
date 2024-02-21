import java.util.Arrays;

public class Leetcode875 {
  public int minEatingSpeed(int[] piles, int h) {
    Arrays.sort(piles);
    int left = 1, right = piles[piles.length-1];
    while (left<right){
      int mid = (left+right)/2;
      int hour = 0;
      for(int pile:piles){
        hour+=pile/mid;
        if (pile%mid!=0) hour++;
      }
      if (hour<=h){
        right = mid;
      }else {
        left = mid+1;
      }
    }
    return left;
  }
}
