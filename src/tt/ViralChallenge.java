package tt;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ViralChallenge {
  private static final long MOD = (long) 1e18 + 7;
  private static final long BASE = 31;

  private static long calculateHash(char c) {
    return (c - 'a' + 1);
  }

  public static int countViralCombinations(String video, List<Integer> engagementArray, int k) {
    Set<Long> uniqueSubstrings = new HashSet<>();
    int cnt = 0;
    int l = 0;
    long currentHash = 0;
    long basePower = 1;

    for (int r = 0; r < video.length(); ++r) {
      int viralValueR = engagementArray.get(video.charAt(r) - 'a');

      if (viralValueR == 0) {
        cnt++;
      }
      while (cnt > k) {
        int viralValueL = engagementArray.get(video.charAt(l) - 'a');
        if (viralValueL == 0) {
          cnt--;
        }
        l++;
      }

      currentHash = 0;
      basePower = 1;
      for (int start = r; start >= l; --start) {
        currentHash = (currentHash + calculateHash(video.charAt(start)) * basePower) % MOD;
        basePower = (basePower * BASE) % MOD;
        uniqueSubstrings.add(currentHash);
      }
    }
    return uniqueSubstrings.size();
  }

  public static void main(String[] args) {
    String video = "abc";
    List<Integer> engagementArray = List.of(0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    int k = 2; // Example value of k

    int result = countViralCombinations(video, engagementArray, k);
    System.out.println(result);
  }
}
