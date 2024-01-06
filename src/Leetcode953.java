public class Leetcode953 {
    public boolean isAlienSorted(String[] words, String order) {
        int[] array = new int[26];
        for (int i = 0; i < order.length(); i++) {
            array[order.charAt(i)-'a'] = i;
        }
        for (int i = 0; i < words.length-1; i++) {
            String word1 = words[i], word2 = words[i+1];
            boolean flag = true;
            for (int j = 0; j < Math.min(word1.length(),word2.length()); j++) {
                if (word1.charAt(j)==word2.charAt(j)) continue;
                else if (array[word1.charAt(j)-'a']>array[word2.charAt(j)-'a']){
                    return false;
                }else{
                    flag = false;
                    break;
                }
            }
            if (flag && word1.length()>word2.length()) return false;
        }
        return true;
    }
}
