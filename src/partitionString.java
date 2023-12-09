/*
Given a string s, partition the string into one or more substrings such that the characters in each substring are unique. That is, no letter appears in a single substring more than once.

Return the minimum number of substrings in such a partition.

Note that each character should belong to exactly one substring in a partition.
 */
public class partitionString {
    public int partitionString(String s) {
        // Set<Character> set = new HashSet<>();
        // int res = 1;
        // for (int i = 0; i < s.length(); i++) {
        //     if (set.contains(s.charAt(i))){
        //         res++;
        //         set = new HashSet<>();
        //         set.add(s.charAt(i));
        //     }else {
        //         set.add(s.charAt(i));
        //     }
        // }
        // return res;

        int[] last_pos = new int[26];
        int partitions = 0;
        int last_end = 0;
        for (int i = 0; i < s.length(); i++) {
            if (last_pos[s.charAt(i) - 'a'] >= last_end) {
                partitions++;
                last_end = i + 1;
            }
            last_pos[s.charAt(i) - 'a'] = i + 1;
        }
        return partitions;
    }
}
