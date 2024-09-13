package tt;

public class BalanceContent {
  public static int balanceContent(String content) {
    if(content.isEmpty()) return 0;
    int[] cnt = new int[26];
    int maxFreq = 0;
    for (char s : content.toCharArray()) {
      cnt[s-'a']++;
      maxFreq = Math.max(maxFreq, cnt[s-'a']);
    }

    int ans = Integer.MAX_VALUE;
    for (int i = 0; i <= 100000; i++) {
      int sum = 0;
      for (int j = 0;j<26;j++) {
        if(cnt[j]!=0){
          sum += Math.min(cnt[j],Math.abs(cnt[j] - i));
        }

      }
      ans = Math.min(ans, sum);
    }

    return ans;

  }
}
