import java.util.*;

public class Main {
  public static void main(String[] args) {
    Main main = new Main();
      System.out.println(main.maxSubstringLength("cdefdc", 3));
  }

    public boolean maxSubstringLength(String s, int k) {
      Map<Character, Integer> first = new HashMap<>();
      Map<Character, Integer> last = new HashMap<>();

      for (int i = 0; i < s.length(); i++) {
          if(!first.containsKey(s.charAt(i))){
              first.put(s.charAt(i), i);
          }
          last.put(s.charAt(i), i);
      }

        for (int i = 0; i < s.length(); i++) {
            int curr = 0;
            int j = i;
            while (j<s.length()){
                boolean flag = false;
                for (int l = j; l < s.length() && l-j+1 <s.length(); l++) {
                    if (isSubstring(s,first,last,j,l)) {
                        curr++;
                        j = l+1;
                        flag = true;
                        break;
                    }
                }
                if(!flag){
                    j++;
                }
            }
            if(curr >= k) return true;
        }
      return false;
    }

    public boolean isSubstring(String s, Map<Character, Integer> first, Map<Character, Integer> last, int start, int end) {
        for (int i = start; i < end; i++) {
            if (first.get(s.charAt(i)) < start || last.get(s.charAt(i)) > end) {
                return false;
            }
        }
        return true;
    }
}
