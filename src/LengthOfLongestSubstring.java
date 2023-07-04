import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LengthOfLongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character,Integer> map = new HashMap();
        int n = s.length();
        if (n == 0){
            return 0;
        }
        int rpointer=0;
        int maxLength=Integer.MIN_VALUE;
        int lpointer=0;
        for(rpointer=0;rpointer<n;rpointer++)
        {
            if(!map.containsKey(s.charAt(rpointer))) {
                map.put(s.charAt(rpointer),rpointer);
            }
            else {
                int minIndex = map.get(s.charAt(rpointer));
                List<Character> list = new ArrayList<>();
                for(char key:map.keySet())
                {
                    if(minIndex>=map.get(key))
                    {
                        list.add(key);
                    }
                }
                for (char ch:list){
                    map.remove(ch);
                }
                lpointer=minIndex+1;
                map.put(s.charAt(rpointer),rpointer);
            }
            int count =rpointer-lpointer+1;
            maxLength =Math.max(count,maxLength);
        }
        return maxLength;
    }
}
