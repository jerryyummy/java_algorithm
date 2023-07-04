import java.util.*;

/*
Given a string s, return the length of the longest substring that contains at most two distinct characters.
 */
public class LengthOfLongestSubstringTwoDistinct {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        HashMap<Character,Integer> map = new HashMap<>();
        HashSet<Character> set = new HashSet<>();
        int start=0, end =-1;
        int result = 0;
        while(end+1<s.length()){
            while(end+1<s.length()){
                if (!set.contains(s.charAt(end+1)) && set.size()==2){
                    break;
                }
                else {
                    map.put(s.charAt(end+1),map.getOrDefault(s.charAt(end+1),0)+1);
                    if (!set.contains(s.charAt(end+1))) set.add(s.charAt(end+1));
                    end++;
                }
            }
            if (result<(end-start+1)){
                result = end-start+1;
            }
            while(set.size()==2){
                if (map.get(s.charAt(start))>1){
                    map.put(s.charAt(start),map.getOrDefault(s.charAt(start),0)-1);
                    start++;
                }else {
                    map.remove(s.charAt(start));
                    set.remove(s.charAt(start));
                    start++;
                    break;
                }
            }
        }
        return result;
    }

}
