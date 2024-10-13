import java.util.Arrays;
import java.util.PriorityQueue;

public class Leetcode3266 {
  public long MOD = 1000000007;
  public int[] getFinalState(int[] nums, int k, int mult) {
    if(mult==1) return nums;

    int n = nums.length;
    long[][] arr = new long[n][2];
    for(int i = 0; i < n; i++) {
      arr[i][0] = nums[i];
      arr[i][1] = i;
    }
    Arrays.sort(arr, (a, b) -> ((a[0]==b[0])?((int)(a[1]-b[1])):(a[0]>b[0]?1:-1)));

    int cnt = 0;
    for(int i = 0; i < n; i++) {
      long x = arr[i][0];
      while(x < arr[n-1][0]) {
        x *= mult;
        cnt++;
      }
    }

    if(k <= cnt) {
      PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> comp(a, b, mult));
      for(int i = 0; i < n; i++) {
        pq.add(new long[]{nums[i], 0, i});
      }
      while(k-->0) {
        long[] curr = pq.remove();
        curr[1]++;
        pq.add(curr);
      }
      int[] res = new int[n];
      while(!pq.isEmpty()) {
        long[] curr = pq.remove();
        res[(int)curr[2]] = (int)((curr[0]*expo(mult, curr[1]))%MOD);
      }
      return res;
    } else {
      for(int i = 0; i < n; i++) {
        while(arr[i][0] < arr[n-1][0]) {
          arr[i][0] = (arr[i][0] * mult);
        }
      }
      k -= cnt;
      Arrays.sort(arr, (a, b) -> ((a[0]==b[0])?((int)(a[1]-b[1])):(a[0]>b[0]?1:-1)));
      int all = k/n;
      int left = k%n;
      for(int i = 0; i < n; i++) {
        arr[i][0] = ((arr[i][0]%MOD) * expo(mult, all))%MOD;
        if(i < left) arr[i][0] = (arr[i][0] * mult)%MOD;
      }
      int[] res = new int[n];
      for(int i = 0; i < n; i++) res[(int)arr[i][1]] = (int)arr[i][0];
      return res;
    }
  }

  public int comp(long[] aa, long[] bb, long m) {
    long a = aa[0];
    long x = aa[1];
    long b = bb[0];
    long y = bb[1];
    if(x >= y) {
      x -= y;
      while(x > 0 && a <= b) {
        a *= m;
        x--;
      }
      if(a == b) {
        return aa[2]>bb[2] ? 1 : -1;
      } else if(a > b) return 1;
      else return -1;
    } else {
      y -= x;
      while(y > 0 && b <= a) {
        b *= m;
        y--;
      }
      if(a == b) {
        return aa[2]>bb[2] ? 1 : -1;
      } else if(a > b) return 1;
      else return -1;
    }
  }

  public long expo(long a, long b) {
    if(b==0) return 1;
    if(b==1) {
      return a%MOD;
    }
    long ans = 1;
    if(b%2 == 1) {
      ans = ((ans * a)%MOD * expo(a, b-1))%MOD;
    } else {
      ans = (expo((a * a)%MOD, b/2))%MOD;
    }
    return ans;
  }
}