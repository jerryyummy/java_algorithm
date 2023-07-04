import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;

/*
Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character
in t (including duplicates) is included in the window. If there is no such substring, return the empty string ""
 */
public class MinWindow {
    public String minWindow(String s, String t) {
        if(t.length() > s.length() || t.length() == 0){
            return "";
        }
        HashMap<Character,Integer> t_map = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            t_map.put(t.charAt(i),t_map.getOrDefault(t.charAt(i),0)+1);
        }
        int end=0,start = 0;
        int matchCount = 0;
        int length = Integer.MAX_VALUE;
        int minStart = 0;
        HashMap<Character,Integer> s_map = new HashMap<>();
        while(end<s.length()){
            if (t_map.containsKey(s.charAt(end))){
                if (s_map.containsKey(s.charAt(end))){
                    if (s_map.get(s.charAt(end))<t_map.get(s.charAt(end))){
                        matchCount++;
                    }
                    s_map.put(s.charAt(end),s_map.get(s.charAt(end))+1);
                }
                else {
                    matchCount++;
                    s_map.put(s.charAt(end),1);
                }
            }
            end++;

            while (matchCount == t.length()){
                if ((end-start)<length){
                    length = end-start;
                    minStart = start;
                }
                if (t_map.containsKey(s.charAt(start))){
                    if (s_map.get(s.charAt(start))>t_map.get(s.charAt(start))){
                        s_map.put(s.charAt(start),s_map.get(s.charAt(start))-1);
                    }else {
                        s_map.put(s.charAt(start),s_map.get(s.charAt(start))-1);
                        matchCount--;
                    }
                }
                start++;
            }
        }
        if (length == Integer.MAX_VALUE){
            return "";
        }else {
            return s.substring(minStart,minStart+length);
        }
    }

}
