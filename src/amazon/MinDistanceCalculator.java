package amazon;

import java.util.Arrays;

public class MinDistanceCalculator {
    public static int getMinDistance(int[] center, int[] destination) {
        // 先排序两个数组
        Arrays.sort(center);
        Arrays.sort(destination);

        int res = 0;
        for (int i = 0; i < center.length; i++) {
            res += Math.abs(center[i] - destination[i]);
        }

        return res;
    }

    public static void main(String[] args) {
        int[] center = {1, 3, 5};
        int[] destination = {2, 4, 6};
        System.out.println(getMinDistance(center, destination)); // 测试代码
    }
}
