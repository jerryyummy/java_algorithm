public class Leetcode2002 {
    int max = 0;
    public int maxProduct(String s) {

        char[] c = s.toCharArray();
        dfs(c, 0, "", "");

        return max;
    }

    public void dfs(char[] c, int i, String s1, String s2){

        if(i >= c.length){

            if(isPalin(s1) && isPalin(s2))
                max = Math.max(max, s1.length()*s2.length());
            return;
        }

        dfs(c, i+1, s1+c[i], s2);
        dfs(c, i+1, s1, s2+c[i]);
        dfs(c, i+1, s1, s2);
    }

    public boolean isPalin(String str){

        int i = 0, j = str.length() - 1;

        while (i < j) {
            if (str.charAt(i) != str.charAt(j))
                return false;
            i++;
            j--;
        }

        return true;
    }

    /**
     public int maxProduct(String s) {
         int[] dp = new int[4096];
         int res = 0, mask = (1 << s.length()) - 1;
         for (int m = 1; m <= mask; ++m)
            dp[m] = palSize(s, m);
         for (int m1 = mask; m1 > 0; --m1)
            if (dp[m1] * (s.length() - dp[m1]) > res)
             for(int m2 = mask ^ m1; m2 > 0; m2 = (m2 - 1) & (mask ^ m1))
                res = Math.max(res, dp[m1] * dp[m2]);
         return res;
     }

     private int palSize(String s, int mask) {
     int p1 = 0, p2 = s.length(), res = 0;
     while (p1 <= p2) {
         if ((mask & (1 << p1)) == 0)
            ++p1;
         else if ((mask & (1 << p2)) == 0)
            --p2;
         else if (s.charAt(p1) != s.charAt(p2))
            return 0;
         else
            res += 1 + (p1++ != p2-- ? 1 : 0);
     }
     return res;
     }
     */
}
