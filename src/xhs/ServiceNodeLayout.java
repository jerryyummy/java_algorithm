package xhs;

import java.util.Arrays;
import java.util.Scanner;

public class ServiceNodeLayout {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        while (T-- > 0) {
            int n = sc.nextInt();
            int[] d = new int[n];
            for (int i = 0; i < n; i++) {
                d[i] = sc.nextInt();
            }

            int[][] intervals = new int[n][2];
            for (int i = 0; i < n; i++) {
                int nodeId = i + 1;
                intervals[i][0] = Math.max(1, nodeId - d[i]);  // start
                intervals[i][1] = Math.min(n, nodeId + d[i]);   // end
            }

            // 按右端点升序排序，右端点相同时按左端点升序
            Arrays.sort(intervals, (a, b) -> {
                if (a[1] != b[1]) return a[1] - b[1];
                return a[0] - b[0];
            });

            int count = 0;
            int lastServicePos = 0; // 上一个服务点的位置

            for (int i = 0; i < n; i++) {
                // 如果当前区间不能被已有服务点覆盖
                if (intervals[i][0] > lastServicePos) {
                    // 贪心：在当前区间的右端点放置服务点
                    lastServicePos = intervals[i][1];
                    count++;
                }
            }

            System.out.println(count);
        }
        sc.close();
    }
}
