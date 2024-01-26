import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class Leetcode3008 {
    void getPatternMatchingIndex(String s, String t, List<Integer> v){
        List<Integer> lps = new ArrayList<>();
        lps.add(0);//Initializing Longest Prefix Suffix (LPS) Array:
        //Building the LPS Array:
        for(int i = 1; i < t.length(); ++i){
            int ind = lps.get(i - 1);//在当前点之前，字符串的最长相同前后缀长度
            while(ind > 0 && t.charAt(ind) != t.charAt(i)) { ind = lps.get(ind - 1); }
            lps.add((t.charAt(ind) == t.charAt(i))?ind + 1 : 0);
        }
        for(int i = 0; i < lps.size(); ++i){
            if(lps.get(i) == t.length()) v.add(i - 2*t.length());
        }
    }

    public List<Integer> beautifulIndices(String s, String a, String b, int k) {
        List<Integer> ans = new ArrayList<>();
        List<Integer> v1 = new ArrayList<>();
        List<Integer> v2 = new ArrayList<>();
        getPatternMatchingIndex(s, a, v1);
        getPatternMatchingIndex(s, b, v2);
        for(int i = 0, j = 0; i < v1.size(); ++i){
            while(j < v2.size() && v1.get(i) > v2.get(j) && Math.abs(v1.get(i) - v2.get(j)) > k) j++;
            if(j < v2.size() && Math.abs(v1.get(i) - v2.get(j)) <= k) ans.add(v1.get(i));
        }
        return ans;
    }
}
