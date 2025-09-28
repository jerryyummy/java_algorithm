package weride;

import java.util.HashSet;

public class PowerNumbers {
    public static int countPowerNumbers(int l, int r) {
        // 1) 生成所有不超过 r 的 a^p (p >= 2)
        HashSet<Integer> set = new HashSet<>();
        set.add(0); // 0 = 0^2
        set.add(1); // 1 = 1^p (p>=2)

        // 从 base=2 开始，连续乘直到超过 r
        for (long a = 2; a * a <= r; a++) {   // a^2 <= r 即可开始
            long val = a * a;                 // p=2 起步
            while (val <= r) {
                set.add((int) val);
                val *= a;                     // p++
            }
        }

        // 2) 转成数组（便于双循环）
        int[] powers = new int[set.size()];
        int idx = 0;
        for (int x : set) powers[idx++] = x;

        // 3) 标记区间 [l, r] 内能表示成 u+v 的数
        boolean[] mark = new boolean[r - l + 1];
        for (int i = 0; i < powers.length; i++) {
            int u = powers[i];
            for (int j = 0; j < powers.length; j++) {
                int v = powers[j];
                int s = u + v;
                if (s < l) continue;
                if (s > r) continue;
                mark[s - l] = true;
            }
        }

        // 4) 统计答案
        int ans = 0;
        for (boolean b : mark) if (b) ans++;
        return ans;
    }

    // 简单示例
    public static void main(String[] args) {
        System.out.println(countPowerNumbers(0,1)); // 例子：输出 3（20,24,25）
    }
}
