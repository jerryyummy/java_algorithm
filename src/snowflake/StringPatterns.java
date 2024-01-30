package snowflake;

public class StringPatterns {
  private static final int MOD = 1000000007;
  private static final int VOWELS = 5; // a, e, i, o, u
  private static final int CONSONANTS = 21; // 26 English letters - 5 vowels

  public static void main(String[] args) {
    int wordLen = 1; // This can be changed to the desired length
    int maxVowels = 1; // This can be changed to the desired max consecutive vowels

    long result = countPatterns(wordLen, maxVowels);
    System.out.println("Number of patterns: " + result);
  }

  public static long countPatterns(int wordLen, int maxVowels) {
    // dp[i][j] will store the count of strings of length i with j consecutive vowels at the end
    long[][] dp = new long[wordLen + 1][maxVowels + 1];
    // Base case: empty string
    dp[0][0] = 1;

    for (int i = 1; i <= wordLen; i++) {
      for (int j = 0; j <= maxVowels; j++) {
        // Add a consonant after j vowels
        dp[i][0] = (dp[i][0] + dp[i - 1][j] * CONSONANTS) % MOD;

        // If we can still add a vowel, add one
        if (j < maxVowels) {
          dp[i][j + 1] = (dp[i][j + 1] + dp[i - 1][j] * VOWELS) % MOD;
        }
      }
    }

    // Sum up all the ways we can end the string, with 0 to maxVowels vowels at the end
    long totalCount = 0;
    for (int i = 0; i <= maxVowels; i++) {
      totalCount = (totalCount + dp[wordLen][i]) % MOD;
    }

    return totalCount;
  }
}



