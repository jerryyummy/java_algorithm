import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

/*
You are given a 0-indexed string s that you must perform k replacement operations on. The replacement operations are given as three 0-indexed parallel arrays,
indices, sources, and targets, all of length k.

To complete the ith replacement operation:

Check if the substring sources[i] occurs at index indices[i] in the original string s.
If it does not occur, do nothing.
Otherwise if it does occur, replace that substring with targets[i].
For example, if s = "abcd", indices[i] = 0, sources[i] = "ab", and targets[i] = "eee", then the result of this replacement will be "eeecd".

All replacement operations must occur simultaneously, meaning the replacement operations should not affect the indexing of each other. The testcases will be generated
such that the replacements will not overlap.

For example, a testcase with s = "abc", indices = [0, 1], and sources = ["ab","bc"] will not be generated because the "ab" and "bc" replacements overlap.
Return the resulting string after performing all replacement operations on s.

A substring is a contiguous sequence of characters in a string.
 */
public class FindReplaceString {
    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        HashMap<Integer,String[]> hashMap = new HashMap<>();
        for (int i = 0; i < sources.length; i++) {
            hashMap.put(indices[i],new String[]{sources[i],targets[i]});
        }
        Arrays.sort(indices);
        String result = "";
        int cur = 0;
        for (int i = 0; i < indices.length; i++) {
            result += s.substring(cur,indices[i]);
            if (s.substring(indices[i], indices[i] + hashMap.get(indices[i])[0].length()).equals(hashMap.get(indices[i])[0])){
                result += hashMap.get(indices[i])[1];
                cur = indices[i]+hashMap.get(indices[i])[0].length();
            }else {
                cur = indices[i];
            }

        }
        result += s.substring(cur);
        return result;
    }

}
