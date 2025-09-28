import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        int T = Integer.parseInt(br.readLine().trim());
        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine().trim());
            int[] deg = new int[n + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 2; i <= n; i++) {
                int p = Integer.parseInt(st.nextToken());
                deg[p]++; // p 的孩子数 +1
            }

            // 任务时长集合：每个有孩子的节点贡献 (deg-1)，再加根的 0
            ArrayList<Integer> tasks = new ArrayList<>();
            for (int u = 1; u <= n; u++) {
                if (deg[u] > 0) tasks.add(deg[u] - 1);
            }
            tasks.add(0); // 根必须点名一次（时长0的任务）

            tasks.sort(Comparator.reverseOrder());
            long ans = 0;
            for (int i = 0; i < tasks.size(); i++) {
                long finish = (long)(i + 1) + tasks.get(i); // 第 i+1 秒启动该任务
                if (finish > ans) ans = finish;
            }
            out.append(ans).append('\n');
        }
        System.out.print(out.toString());
    }
}