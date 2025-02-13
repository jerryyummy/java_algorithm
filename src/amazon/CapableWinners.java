package amazon;

import java.util.Arrays;

public class CapableWinners {
    public static int getWinnerCount(int[] boosterA, int[] boosterB, int[] boosterC) {
        int n = boosterA.length;
        int[] sortA = new int[n];
        int[] sortB = new int[n];
        int[] sortC = new int[n];

        int maxA = 0;
        int maxB = 0;

        // 处理数据，找出 maxA 和 maxB
        for (int i = 0; i < n; i++) {
            int[] temp = {boosterA[i], boosterB[i], boosterC[i]};
            Arrays.sort(temp);

            sortA[i] = temp[0];
            sortB[i] = temp[1];
            sortC[i] = temp[2];

            maxA = Math.max(maxA, temp[0]);
            maxB = Math.max(maxB, temp[1]);
        }

        int count = 0;

        // 计算满足条件的个数
        for (int i = 0; i < n; i++) {
            if (sortB[i] > maxA && sortC[i] > maxB) {
                count++;
            }
        }

        return count;
    }
}
