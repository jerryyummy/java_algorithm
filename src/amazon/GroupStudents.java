package amazon;

import java.util.Arrays;

public class GroupStudents {
    public static int groupStudents(int[] levels, int maxSpread) {
        if (levels == null || levels.length == 0) {
            return 0;
        }

        Arrays.sort(levels); // 对数组排序
        int count = 1;
        int minLevel = levels[0];

        for (int i = 1; i < levels.length; i++) {
            if (levels[i] - minLevel > maxSpread) {
                count++;
                minLevel = levels[i];
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[] levels = {1, 5, 3, 9, 2};
        int maxSpread = 3;
        System.out.println(groupStudents(levels, maxSpread)); // 输出 3
    }
}
