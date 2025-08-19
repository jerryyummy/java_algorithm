package baidu;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

class GridPathMinCost {

    static class State implements Comparable<State> {
        int x, y, cost;
        State(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
        @Override
        public int compareTo(State other) {
            return Integer.compare(this.cost, other.cost);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while (T-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int[][] grid = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    grid[i][j] = sc.nextInt();
                }
            }
            System.out.println(solve(grid, n, m));
        }
        sc.close();
    }

    public static int solve(int[][] grid, int n, int m) {
        int[][] dist = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        PriorityQueue<State> pq = new PriorityQueue<>();
        dist[0][0] = 0;
        pq.offer(new State(0, 0, 0));

        int[] dirs = {-1, 0, 1, 0, -1}; // 四邻域方向

        while (!pq.isEmpty()) {
            State curr = pq.poll();
            int x = curr.x, y = curr.y, cost = curr.cost;

            // 到达终点直接返回（Dijkstra保证首次取出终点时cost最小）
            if (x == n-1 && y == m-1) return cost;
            // 跳过非最优状态
            if (cost > dist[x][y]) continue;

            // 方式1：四邻域移动（代价1）
            for (int d = 0; d < 4; d++) {
                int nx = x + dirs[d], ny = y + dirs[d+1];
                if (nx >=0 && nx <n && ny >=0 && ny <m) {
                    if (dist[nx][ny] > cost + 1) {
                        dist[nx][ny] = cost + 1;
                        pq.offer(new State(nx, ny, dist[nx][ny]));
                    }
                }
            }

            // 方式2：异或移动（优化：只处理能减少总代价的跳跃）
            int currVal = grid[x][y];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (i == x && j == y) continue;
                    int xorCost = currVal ^ grid[i][j];
                    int newCost = cost + xorCost;
                    // 剪枝：只有新代价能优化当前记录才处理
                    if (newCost < dist[i][j]) {
                        dist[i][j] = newCost;
                        pq.offer(new State(i, j, newCost));
                    }
                }
            }
        }
        // 理论上不会执行到此处（题目保证有解）
        return dist[n-1][m-1];
    }
}
