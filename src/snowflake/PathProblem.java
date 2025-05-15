package snowflake;

import java.util.*;

public class PathProblem {
    static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static List<Integer> findOptimalPair(int n, int m, List<List<Integer>> blockedPositions) {
        boolean[][] grid = new boolean[n + 1][m + 1];
        for (List<Integer> pos : blockedPositions) {
            grid[pos.get(0)][pos.get(1)] = true;
        }
        if (grid[1][1] || grid[n][m]) {
            List<Integer> res = new ArrayList<>();
            res.add(-1);
            res.add(-1);
            return res;
        }

        int[][] minDist = new int[n][m];
        for (int[] row : minDist) Arrays.fill(row, Integer.MAX_VALUE);

        Queue<int[]> queue = new LinkedList<>();
        for (List<Integer> pos : blockedPositions) {
            int x = pos.get(0) - 1;
            int y = pos.get(1) - 1;
            queue.add(new int[]{x, y});
            minDist[x][y] = 0;
        }

        // Multi-source BFS: compute min distance to nearest blocked cell
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int[] dir : dirs) {
                int nx = cur[0] + dir[0];
                int ny = cur[1] + dir[1];
                if (nx >= 0 && ny >= 0 && nx < n && ny < m && minDist[nx][ny] > minDist[cur[0]][cur[1]] + 1) {
                    minDist[nx][ny] = minDist[cur[0]][cur[1]] + 1;
                    queue.add(new int[]{nx, ny});
                }
            }
        }

        // Binary search for maximum strength
        int left = 0, right = n + m - 2, maxStrength = -1, minSteps = Integer.MAX_VALUE;
        while (left <= right) {
            int mid = (left + right) / 2;
            int steps = bfs(n, m, minDist, mid, grid);
            if (steps != -1) {
                maxStrength = mid;
                minSteps = steps;
                left = mid + 1; // try to find stronger path
            } else {
                right = mid - 1;
            }
        }

        List<Integer> result = new ArrayList<>();
        if (maxStrength == -1) {
            result.add(-1);
            result.add(-1);
        } else {
            result.add(maxStrength);
            result.add(minSteps);
        }
        return result;
    }

    private static int bfs(int n, int m, int[][] minDist, int threshold, boolean[][] grid) {
        if (minDist[0][0] < threshold || minDist[n - 1][m - 1] < threshold) return -1;

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];
        queue.add(new int[]{0, 0, 1});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0], y = cur[1], steps = cur[2];
            if (x == n - 1 && y == m - 1) return steps;
            for (int[] dir : dirs) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                if (nx >= 0 && ny >= 0 && nx < n && ny < m &&!visited[nx][ny]
                        && minDist[nx][ny] >= threshold &&!grid[nx + 1][ny + 1]) {
                    visited[nx][ny] = true;
                    queue.add(new int[]{nx, ny, steps + 1});
                }
            }
        }
        return -1;
    }
}
