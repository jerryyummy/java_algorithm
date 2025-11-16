package intuit;

public class PaintHouse {
    static final int MOD = 1000000007;

    public static int countPatterns(int n) {
        // 枚举所有长度为 3、且不全相同的行模式（24 种）
        int[][] rows = new int[24][3];
        int idx = 0;
        for (int a = 0; a < 3; a++) {
            for (int b = 0; b < 3; b++) {
                for (int c = 0; c < 3; c++) {
                    if (a == b && b == c) continue;  // 排除 RRR / GGG / BBB
                    rows[idx][0] = a;
                    rows[idx][1] = b;
                    rows[idx][2] = c;
                    idx++;
                }
            }
        }
        int m = idx; // 24

        // dp[rowPattern][mask]：当前到达这一行、上一行模式是 rowPattern，
        // 且三列“是否已经出现过不同颜色”的状态为 mask 时的方案数
        long[][] dp = new long[m][8];

        // 第一行：任意行模式都可以，此时每列只有一种颜色，mask = 000
        for (int p = 0; p < m; p++) {
            dp[p][0] = 1;
        }

        // 从第二行开始逐行转移
        for (int r = 2; r <= n; r++) {
            long[][] next = new long[m][8];

            for (int old = 0; old < m; old++) {
                int[] prevRow = rows[old];
                for (int mask = 0; mask < 8; mask++) {
                    long ways = dp[old][mask];
                    if (ways == 0) continue;

                    for (int cur = 0; cur < m; cur++) {
                        int[] curRow = rows[cur];
                        int newMask = mask;

                        // 第 0 列 -> bit 0
                        if (((mask & 1) == 0) && prevRow[0] != curRow[0]) newMask |= 1;
                        // 第 1 列 -> bit 1
                        if (((mask & 2) == 0) && prevRow[1] != curRow[1]) newMask |= 2;
                        // 第 2 列 -> bit 2
                        if (((mask & 4) == 0) && prevRow[2] != curRow[2]) newMask |= 4;

                        next[cur][newMask] = (next[cur][newMask] + ways) % MOD;
                    }
                }
            }
            dp = next;
        }

        // 最后一行后，三列都已经出现过不同颜色 => mask = 111 (即 7)
        long ans = 0;
        int fullMask = 7;
        for (int p = 0; p < m; p++) {
            ans = (ans + dp[p][fullMask]) % MOD;
        }
        return (int) ans;
    }

}
