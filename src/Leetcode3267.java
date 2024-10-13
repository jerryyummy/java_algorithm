public class Leetcode3267 {
  public int countPairs(int[] nums) {
    int n = nums.length;
    int cnt = 0;
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        if (eql(nums[i], nums[j])) {

          cnt++;
        }
      }
    }
    return cnt;
  }

  public static boolean eql(int x, int y) {
    int[] a1 = arr(x);
    int[] a2 = arr(y);
    int[] mis = new int[4];
    int cnt = 0;
    for (int k = 0; k < 8; k++) {
      if (a1[k] != a2[k]) {
        if (cnt == 4)
          return false;
        mis[cnt] = k;
        cnt++;
      }
    }
    if (cnt == 0)
      return true;
    else if (cnt == 2) {
      return a1[mis[0]] == a2[mis[1]] && a1[mis[1]] == a2[mis[0]];

    } else if(cnt==3){
      int a=mis[0];
      int b=mis[1];
      int c=mis[2];
      return (a1[a]==a2[b] && a1[b]==a2[c]&& a1[c]==a2[a])||(a1[a]==a2[c] && a1[c]==a2[b]&& a1[b]==a2[a]);
    }
    else if (cnt == 4) {
      int a=mis[0];
      int b=mis[1];
      int c=mis[2];
      int d=mis[3];
      return(a1[a]==a2[b]&& a1[b]==a2[a] && a1[c]==a2[d]&& a1[d]==a2[c])||
          (a1[a]==a2[c]&& a1[c]==a2[a] && a1[b]==a2[d]&& a1[d]==a2[b])||
          (a1[a]==a2[d]&& a1[d]==a2[a] && a1[b]==a2[c]&& a1[c]==a2[b]);
    } else {
      return false;
    }
  }

  public static int[] arr(int num) {
    int[] cnt = new int[8];
    int i = 0;
    while (num > 0) {
      cnt[i] = num % 10;
      num /= 10;
      i++;
    }
    return cnt;
  }
}