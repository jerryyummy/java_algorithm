import java.util.ArrayList;
import java.util.List;

public class Leetcode1931 {
    private static final int MOD = 1_000_000_007;

    public int colorTheGrid(int m, int n) {
        // 把较小的一边作为高度
        int h = Math.min(m, n);
        int w = Math.max(m, n);

        // 1. 枚举所有高度为 h 的合法一列状态
        // 3 种颜色：0,1,2
        List<int[]> states = new ArrayList<>();
        dfsBuildStates(h, 0, -1, new int[h], states);
        int S = states.size();

        // 2. 预处理相邻列状态是否兼容
        boolean[][] ok = new boolean[S][S];
        for (int i = 0; i < S; i++) {
            for (int j = 0; j < S; j++) {
                if (compatible(states.get(i), states.get(j))) {
                    ok[i][j] = true;
                }
            }
        }

        // 3. DP：按“列”推进
        long[] dpPrev = new long[S];
        long[] dpCur  = new long[S];

        // 第一列：任意状态都可以
        for (int i = 0; i < S; i++) {
            dpPrev[i] = 1;
        }

        // 从第二列开始
        for (int col = 1; col < w; col++) {
            // 清零当前列
            java.util.Arrays.fill(dpCur, 0L);

            for (int prev = 0; prev < S; prev++) {
                if (dpPrev[prev] == 0) continue;
                long ways = dpPrev[prev];

                for (int cur = 0; cur < S; cur++) {
                    if (!ok[prev][cur]) continue;
                    dpCur[cur] = (dpCur[cur] + ways) % MOD;
                }
            }

            // 滚动数组
            long[] tmp = dpPrev;
            dpPrev = dpCur;
            dpCur = tmp;
        }

        long ans = 0;
        for (int i = 0; i < S; i++) {
            ans = (ans + dpPrev[i]) % MOD;
        }

        return (int) ans;
    }

    // DFS 枚举一列的所有合法状态：上一行颜色不能相同
    private void dfsBuildStates(int h, int row, int prevColor,
                                int[] curr, List<int[]> states) {
        if (row == h) {
            states.add(curr.clone());
            return;
        }
        for (int color = 0; color < 3; color++) {
            if (color == prevColor) continue; // 竖直相邻不能同色
            curr[row] = color;
            dfsBuildStates(h, row + 1, color, curr, states);
        }
    }

    // 判断两列是否可以相邻：同一行上的颜色不能相同
    private boolean compatible(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == b[i]) return false;
        }
        return true;
    }
}
