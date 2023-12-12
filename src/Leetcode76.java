import java.util.HashMap;

public class Leetcode76 {
    public String minWindow(String s, String t){
        int n = s.length(), m = t.length();
        String ans="";
        if(m>n || n==0 ){
            return "";
        }
        HashMap<Character,Integer> map = new HashMap<>();//record the frequency for t
        for (int i = 0; i < m; i++) {
            map.put(t.charAt(i),map.getOrDefault(t.charAt(i),0)+1);
        }

        HashMap<Character,Integer> subString = new HashMap<>();//record the frequency for substring in s
        int start = 0, end = 0;
        int matchCount=0; // 记录有多少个字符匹配
        int minLen=Integer.MAX_VALUE,minStart=0;

        while (end<n){
            //acquire characters
            char ch=s.charAt(end);
            if(map.containsKey(ch)){
                if(subString.containsKey(ch)){
                    if(subString.get(ch)<map.get(ch)){
                        matchCount++;
                    }
                    subString.put(ch,subString.get(ch)+1);
                }
                else{
                    matchCount++;
                    subString.put(ch,1);
                }
            }
            end++;

            //release chracters till matchCount=n and update length after each char
            while(matchCount==m){
                if((end-start)<minLen){
                    minLen=end-start;
                    minStart=start;
            }
                char chS=s.charAt(start);
                if(map.containsKey(chS)){
                    if(subString.get(chS)>map.get(chS)){
                        subString.put(chS,subString.get(chS)-1);
                    }
                    else{
                        subString.put(chS,subString.get(chS)-1);
                        matchCount--;
                    }
                }
                start++;
            }
        }

        if (minLen == Integer.MAX_VALUE) return ans;
        return s.substring(minStart,minStart+minLen);
    }
}
