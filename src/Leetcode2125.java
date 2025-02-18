public class Leetcode2125 {
    public int numberOfBeams(String[] bank) {
        int res = 0;
        int cur = 0;
        for (int i = 0; i < bank.length; i++) {
            int temp = 0;
            for (int j = 0; j < bank[i].length(); j++) {
                if(bank[i].charAt(j) == '1'){
                    temp++;
                }
            }
            if(temp == 0) continue;
            res += cur*temp;
            cur = temp;
        }
        return res;
    }
}
