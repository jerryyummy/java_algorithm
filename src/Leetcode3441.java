public class Leetcode3441 {
    public String minCostGoodCaption(String S) {
        int n = S.length();
        if (n < 3) {
            return "";
        }

        char[] s = S.toCharArray();
        int[][] f = new int[n + 1][26]; // f[i][j]: 以 j ('a'+j) 结尾的最小修改代价
        int[] minJ = new int[n + 1]; // 记录最小修改代价对应的字符索引
        int[][] nxt = new int[n + 1][26]; // 记录 f[i][j] 由哪个 j 变换而来

        for (int i = n - 1; i >= 0; i--) {
            int mn = Integer.MAX_VALUE;
            for (int j = 0; j < 26; j++) {
                int res = f[i + 1][j] + Math.abs(s[i] - 'a' - j);
                int res2 = i <= n - 6 ? f[i + 3][minJ[i + 3]] + Math.abs(s[i] - 'a' - j) + Math.abs(s[i + 1] - 'a' - j) + Math.abs(s[i + 2] - 'a' - j) : Integer.MAX_VALUE;
                if (res2 < res || res2 == res && minJ[i + 3] < j) {
                    res = res2;
                    nxt[i][j] = minJ[i + 3]; // 记录转移来源
                } else {
                    nxt[i][j] = j; // 记录转移来源
                }
                f[i][j] = res;
                if (res < mn) {
                    mn = res;
                    minJ[i] = j; // 记录最小的 f[i][j] 中的 j 是多少
                }
            }
        }

        char[] ans = new char[n];
        int i = 0;
        int j = minJ[0];
        while (i < n) {
            ans[i] = (char) ('a' + j);
            int k = nxt[i][j];
            if (k == j) {
                i++;
            } else {
                ans[i + 2] = ans[i + 1] = ans[i];
                i += 3;
                j = k;
            }
        }
        return new String(ans);
    }
}
