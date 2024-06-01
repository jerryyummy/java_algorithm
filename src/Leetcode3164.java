import java.util.HashMap;
import java.util.Map;

public class Leetcode3164 {
  public long numberOfPairs(int[] nums1, int[] nums2, int k) {
    Map<Integer, Integer> divisorCount = new HashMap<>();
    for(int i : nums2){
      int val = i * k;
      divisorCount.put(val, divisorCount.getOrDefault(val, 0) + 1);
    }
    long ans=0;
    for(int i : nums1){
      for(int j=1;j*j<=i;j++){
        if(i%j==0){
          if(divisorCount.containsKey(j)){
            ans += divisorCount.get(j);
          }
          int val=i/j;
          if(j!=val && divisorCount.containsKey(val)){
            ans += divisorCount.get(val);
          }
        }
      }
    }
    return ans;
  }
}
