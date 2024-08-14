public class Leetcode560 {
  public int subarraySum(int[] nums, int k) {
    int n = nums.length;
    int[] prefixSum = new int[n + 1];
    prefixSum[0] = 0;
    for (int i = 0; i < n; i++) {
      prefixSum[i+1] = prefixSum[i] + nums[i];
    }

    int res = 0;
    for (int i = 0; i < n+1; i++) {
      for (int j = i+1; j < n+1; j++) {
        if(prefixSum[j+1] - prefixSum[i+1] == k){
          res++;
        }
      }
    }
    return res;
  }

  /*
  public int subarraySum(int[] nums, int k) {
        int count = 0, sum = 0;
        HashMap <Integer, Integer> map = new HashMap <> ();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k))
                count += map.get(sum - k);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
   */

}
