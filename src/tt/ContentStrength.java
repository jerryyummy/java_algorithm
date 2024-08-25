package tt;

import java.util.HashSet;

public class ContentStrength {
  static final long MOD = (long) 1e18 + 7;
  static final long BASE = 31;

  static boolean isConsonant(char c) {
    return !(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u');
  }

  static long calculateHash(String s) {
    long hash = 0;
    for (char c : s.toCharArray()) {
      hash = (hash * BASE + (c - 'a' + 1)) % MOD;
    }
    return hash;
  }

  public static long calculateContentStrength(String clip) {
    int n = clip.length();
    if (n == 0) return 0;

    HashSet<Long> uniqueHashes = new HashSet<>();
    long contentStrength = 0;

    for (int i = 0; i < n; ++i) {
      String subStr = "";
      long currentHash = 0;
      long basePower = 1;

      for (int j = 0; j < n; ++j) {
        char currentChar = clip.charAt((i + j) % n);
        if (isConsonant(currentChar)) {
          subStr += currentChar;
          currentHash = (currentHash * BASE + (currentChar - 'a' + 1)) % MOD;
          basePower = (basePower * BASE) % MOD;
          if (!uniqueHashes.contains(currentHash)) {
            uniqueHashes.add(currentHash);
            contentStrength++;
          }
        } else {
          break;
        }
      }
    }

    return contentStrength;
  }

  public static void main(String[] args) {
    System.out.println(calculateContentStrength("bac"));
  }
}
