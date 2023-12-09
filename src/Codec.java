import java.util.ArrayList;
import java.util.List;

class Codec {

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        String res = "";
        for(String str:strs){
            res = res + String.valueOf(str.length())+"#";
            res+=str;
        }
        return res;
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> res = new ArrayList<>();
        int i = 0;
        while(i<s.length()){
            int j = i;
            while(Character.isDigit(s.charAt(j))) j++;
            int length = Integer.parseInt(s.substring(i,j));
            String temp = s.substring(j+1,j+1+length);
            res.add(temp);
            i = j+1+length;
        }
        return res;
    }
}
