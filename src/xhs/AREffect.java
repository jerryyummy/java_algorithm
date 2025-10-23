package xhs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/*
题目描述在小红书“品牌创意工坊”中，营销人员可以为直播和短视频活动创建定制化丝带AR特效，结合品牌ID与礼盒包装场景，实现动态丝带动画。
为了支撑亿级日活的前端渲染，后端需要在活动发布时预先计算并缓存所有可能的切割方案数，确保小程序组件和Web端秒级响应。
现有一根虚拟丝带长度为k，可以将其分割成若干段或保持一整段不动，但是每段长度只能取整数a、b或c中的一个，
且不允许任何长度为a的段后面直接跟随长度为c的段。
请对所有长度k (1<=k<=n)，统计合法的切割方案数，供小红书前端组件批量加载与渲染。
由于答案可能很大，请将答案对(10^9+7)取模后输出。顺序不同视为不同方案。

输入描述
每个测试文件均包含多组测试数据。第一行输入一个整数T (1<=T<10000)代表数据组数。
每组测试数据描述如下：
在一行上输入四个整数n,a,b,c(1<=n,a,b,c<=10^6)，代表最大丝带长度、可选分段长度a,b,c。保证a,b,c两两互不相等。
除此之外，保证单个测试文件的n之和不超过10^6。

输出描述
对于每组测试数据，新起一行，输出n个整数，其中第k个数表示长度为k的丝带的合法分割方案数对 10^9+7 取模的结果。
 */
public class AREffect {
    static final int MOD = (int) 1e9 + 7;

    static void work(BufferedReader br, PrintWriter pw) throws IOException {
        String[] parts = br.readLine().split(" ");
        int n = Integer.parseInt(parts[0]);
        int a = Integer.parseInt(parts[1]);
        int b = Integer.parseInt(parts[2]);
        int c = Integer.parseInt(parts[3]);

        int[] dp = new int[n + 1];
        dp[0] = 1;

        // 核心DP状态转移
        for (int i = 1; i <= n; i++) {
            // 处理a的贡献
            if (i >= a) {
                dp[i] = (dp[i] + dp[i - a]) % MOD;
            }
            // 处理b的贡献
            if (i >= b) {
                dp[i] = (dp[i] + dp[i - b]) % MOD;
            }
            // 处理c的贡献（需减去i-c-a的情况）
            if (i >= c) {
                int total = dp[i - c];
                int endA = (i - c >= a) ? dp[i - c - a] : 0;
                int valid = (total - endA + MOD) % MOD; // +MOD避免负数
                dp[i] = (dp[i] + valid) % MOD;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(dp[i]).append(" ");
        }
        pw.println(sb.deleteCharAt(sb.length() - 1));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            work(br, pw);
        }

        pw.flush();
        br.close();
        pw.close();
    }
}
