public class Leetcode3223 {
    public int minimumLength(String s) {
        int[] table = new int[26];
        for (char c : s.toCharArray()) {
            int index = c - 'a';
            if (table[index] >= 2) {
                table[index] = 1;
            }else {
                table[index]++;
            }
        }
        int res = 0;
        for (int i = 0; i < 26; i++) {
            res += table[i];
        }
        return res;
    }
}
