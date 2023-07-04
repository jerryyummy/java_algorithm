/*
Sometimes people repeat letters to represent extra feeling. For example:

"hello" -> "heeellooo"
"hi" -> "hiiii"
In these strings like "heeellooo", we have groups of adjacent letters that are all the same: "h", "eee", "ll", "ooo".

You are given a string s and an array of query strings words. A query word is stretchy if it can be made to be equal to s by any number of applications of the following
extension operation: choose a group consisting of characters c, and add some number of characters c to the group so that the size of the group is three or more.

For example, starting with "hello", we could do an extension on the group "o" to get "hellooo", but we cannot get "helloo" since the group "oo" has a size less than three.
Also, we could do another extension like "ll" -> "lllll" to get "helllllooo". If s = "helllllooo", then the query word "hello" would be stretchy because of these two extension
operations: query = "hello" -> "hellooo" -> "helllllooo" = s.
Return the number of query strings that are stretchy.
 */
public class ExpressiveWords {
    public int expressiveWords(String S, String[] words) {
        int result = 0;
        for (int i = 0; i < words.length; ++i) {
            if (isStretchy(S, words[i])) result++;
        }
        return result;
    }

    private boolean isStretchy(String S, String word) {
        int i = 0, j = 0;
        while (i < S.length() && j < word.length()) {
            if (S.charAt(i) != word.charAt(j)) return false;    // Characters don't match.
            int sBlockSize = 1;
            i++;
            // Count block size starting at S.charAt(i);
            while (i < S.length() && S.charAt(i - 1) == S.charAt(i)) {
                sBlockSize++;
                i++;
            }
            int wordBlockSize = 1;
            j++;
            // Count block size starting at word.charAt(j);
            while (j < word.length() && word.charAt(j - 1) == word.charAt(j)) {
                wordBlockSize++;
                j++;
            }

            if (sBlockSize < wordBlockSize || (wordBlockSize < sBlockSize && sBlockSize < 3)) {
                return false;
            }
        }
        return i == S.length() && j == word.length();
    }
}
