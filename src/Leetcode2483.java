public class Leetcode2483 {
    public int bestClosingTime(String customers) {
        int come = 0;
        for (int i = 0; i < customers.length(); i++) {
            if (customers.charAt(i) == 'Y') {
                come++;
            }
        }

        int res = Integer.MAX_VALUE;
        int penalty = 0;
        int index = 0;
        for (int i = 0; i < customers.length(); i++) {
            if(penalty + come < res){
                res = penalty + come;
                index = i;
            }
            if (customers.charAt(i) == 'N') {
                penalty++;
            }else{
                come--;
            }
        }
        if(penalty < res){
            index = customers.length();
        }
        return index;
    }
}
