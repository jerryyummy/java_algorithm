import java.util.ArrayList;
import java.util.Collections;

public class Leetcode2959 {

    private static int getMaxDistance(int mask, int n, ArrayList<ArrayList<Integer>> d) {
        int res = 0;
        for (int k = 0; k < n; ++k) { // Floyd-Warshall
            if ((mask & (1 << k)) != 0) {// 看这个顶点是否被close
                for (int i = 0; i < n; ++i) {
                    if (i != k && (mask & (1 << i)) != 0) {
                        for (int j = 0; j < n; ++j) {
                            if (j != i && (mask & (1 << j)) != 0) {
                                d.get(i).set(j, Math.min(d.get(i).get(j), d.get(i).get(k) + d.get(k).get(j)));
                            }
                        }
                    }
                }
            }
        }

        // 找出最大距离
        for (int i = 0; i < n; ++i) {
            if ((mask & (1 << i)) != 0) {
                for (int j = i + 1; j < n; ++j) {
                    if ((mask & (1 << j)) != 0) {
                        res = Math.max(res, d.get(i).get(j));
                    }
                }
            }
        }
        return res;
    }

    // 计算在给定最大距离下的集合数量
    public static int numberOfSets(int n, int maxDistance, int[][] roads) {
        // 初始化原始距离矩阵
        ArrayList<ArrayList<Integer>> originalD = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> row = new ArrayList<>(Collections.nCopies(n, 100000));
            originalD.add(row);
        }

        for (int[] r : roads) {
            int value = Math.min(originalD.get(r[0]).get(r[1]), r[2]);
            originalD.get(r[0]).set(r[1], value);
            originalD.get(r[1]).set(r[0], value);
        }

        int comb = 1 << n, res = 1;
        for (int mask = 1; mask < comb; ++mask) {
            // 创建距离矩阵的副本
            ArrayList<ArrayList<Integer>> dCopy = new ArrayList<>();
            for (ArrayList<Integer> row : originalD) {
                dCopy.add(new ArrayList<>(row));
            }

            if (getMaxDistance(mask, n, dCopy) <= maxDistance) {
                res++;
            }
        }
        return res;
    }
}
