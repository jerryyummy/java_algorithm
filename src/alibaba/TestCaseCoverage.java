package alibaba;

import java.util.*;

public class TestCaseCoverage {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int count = scanner.nextInt();

        for (int c = 0; c < count; c++) {
            int N = scanner.nextInt();
            int M = scanner.nextInt();

            int[] times = new int[M];
            int[][] coverage = new int[M][];

            for (int i = 0; i < M; i++) {
                times[i] = scanner.nextInt();

                String line = scanner.nextLine().trim();
                String[] parts = line.split("\\s+");

                List<Integer> modules = new ArrayList<>();
                for (String part : parts) {
                    if (!part.isEmpty()) {
                        modules.add(Integer.parseInt(part));
                    }
                }

                coverage[i] = new int[modules.size()];
                for (int j = 0; j < modules.size(); j++) {
                    coverage[i][j] = modules.get(j);
                }
            }

            int result = findMinTime(N, M, times, coverage);
            System.out.println(result);
        }

        scanner.close();
    }

    private static int findMinTime(int N, int M, int[] times, int[][] coverage) {
        int[] dp = new int[1 << N];
        Arrays.fill(dp, Integer.MAX_VALUE / 2);
        dp[0] = 0;

        int[] masks = new int[M];
        for (int i = 0; i < M; i++) {
            for (int module : coverage[i]) {
                masks[i] |= (1 << module);
            }
        }

        for (int i = 0; i < M; i++) {
            for (int state = (1 << N) - 1; state >= 0; state--) {
                if (dp[state] >= Integer.MAX_VALUE / 2) continue;

                int newState = state | masks[i];
                dp[newState] = Math.min(dp[newState], dp[state] + times[i]);
            }
        }

        return dp[(1 << N) - 1] >= Integer.MAX_VALUE / 2 ? -1 : dp[(1 << N) - 1];
    }
}
