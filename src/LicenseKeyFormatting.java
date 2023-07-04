import java.util.*;

/*
You are given a license key represented as a string s that consists of only alphanumeric characters and dashes. The string is separated into n + 1
groups by n dashes. You are also given an integer k.

We want to reformat the string s such that each group contains exactly k characters, except for the first group, which could be shorter than k but
still must contain at least one character. Furthermore, there must be a dash inserted between two groups, and you should convert all lowercase letters to uppercase
 */
public class LicenseKeyFormatting {
    public String licenseKeyFormatting(String s, int k) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--)
            if (s.charAt(i) != '-')
                sb.append(sb.length() % (k + 1) == k ? '-' : "").append(s.charAt(i));
        return sb.reverse().toString().toUpperCase();
    }

    public int totalFruit(int[] fruits) {
        HashMap<Integer, Integer> map_Data = new HashMap<Integer, Integer>();
        for (int i = 0; i < fruits.length; i++) {
            if (map_Data.containsKey(fruits[i])){
                int value = map_Data.get(fruits[i])+1;
                map_Data.replace(fruits[i],value);
            }else {
                map_Data.put(fruits[i],1);
            }
        }
        List<Map.Entry<Integer, Integer>> list_Data = new ArrayList<Map.Entry<Integer,Integer>>(map_Data.entrySet());
        Collections.sort(list_Data, new Comparator<Map.Entry<Integer,Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                if ((o2.getKey() - o1.getKey())>0)
                    return 1;
                else if((o2.getKey() - o1.getKey())==0)
                    return 0;
                else
                    return -1;
            }
        });
        if (fruits.length==1){
            return fruits[0];
        }
        else {
            return list_Data.get(0).getValue() +list_Data.get(1).getValue();
        }
    }

    public static void main(String[] args) {
        LicenseKeyFormatting oddEvenJump = new LicenseKeyFormatting();
        System.out.println(oddEvenJump.totalFruit(new int[]{0, 1, 2, 2}));
    }
}
