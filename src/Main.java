import java.util.*;

public class Main {
  static class State {
    int x, y, z, coins, energy;
    List<int[]> path;

    State(int x, int y, int z, int coins, int energy, List<int[]> path) {
      this.x = x;
      this.y = y;
      this.z = z;
      this.coins = coins;
      this.energy = energy;
      this.path = new ArrayList<>(path);
      this.path.add(new int[] {x, y, z});
    }

    int getRemainingValue() {
      return this.coins * this.energy;
    }
  }

  static int[][] directions = {
      {1, 0, 0}, {-1, 0, 0},
      {0, 1, 0}, {0, -1, 0},
      {0, 0, 1}, {0, 0, -1}
  };

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int v = scanner.nextInt();
    int e = scanner.nextInt();
    scanner.nextLine();  // Consume the newline character

    int[][][] gridCoins = new int[n][n][n];
    int[][][] gridEnergy = new int[n][n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        for (int k = 0; k < n; k++) {
          gridCoins[i][j][k] = scanner.nextInt();
        }
      }
    }
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        for (int k = 0; k < n; k++) {
          gridEnergy[i][j][k] = scanner.nextInt();
        }
      }
    }

    Result result = bfs(gridCoins, gridEnergy, n, v, e);
    if (result.reachedEnd) {
      System.out.println(result.maxRemainingValue);
      for (int[] p : result.path) {
        System.out.println((p[2] + 1) + " " + (p[1] + 1) + " " + (p[0] + 1));
      }
    } else {
      System.out.println(-1);
      System.out.println((result.closest[0] + 1) + " " + (result.closest[1] + 1) + " " + (result.closest[2] + 1));
    }
  }

  public static Result bfs(int[][][] gridCoins, int[][][] gridEnergy, int n, int v, int e) {
    Queue<State> queue = new PriorityQueue<>((a,b)->b.coins*b.energy - a.coins*a.energy);
    boolean[][][] visited = new boolean[n][n][n];
    queue.offer(new State(0, 0, 0, v, e, new ArrayList<>()));
    visited[0][0][0] = true;

    int maxRemainingValue = -1;
    List<int[]> bestPath = null;
    int[] closest = {0, 0, 0};
    int minDistance = Integer.MAX_VALUE;

    while (!queue.isEmpty()) {
      State current = queue.poll();

      if (current.x == n - 1 && current.y == n - 1 && current.z == n - 1) {
        int remainingValue = current.getRemainingValue();
        if (remainingValue > maxRemainingValue) {
          maxRemainingValue = remainingValue;
          bestPath = current.path;
        }
        continue;
      }

      for (int[] direction : directions) {
        int nx = current.x + direction[0];
        int ny = current.y + direction[1];
        int nz = current.z + direction[2];

        if (nx >= 0 && nx < n && ny >= 0 && ny < n && nz >= 0 && nz < n) {
          if (gridCoins[nx][ny][nz] == -1 || gridEnergy[nx][ny][nz] == -1) {
            continue;
          }

          int nextCoins = current.coins - gridCoins[nx][ny][nz];
          int nextEnergy = current.energy - gridEnergy[nx][ny][nz];

          if (nextCoins < 0 || nextEnergy < 0) {
            continue;
          }

          if (!visited[nx][ny][nz]) {
            visited[nx][ny][nz] = true;
            queue.offer(new State(nx, ny, nz, nextCoins, nextEnergy, current.path));
          }

          int distanceToEnd = Math.abs(nx - (n - 1)) + Math.abs(ny - (n - 1)) + Math.abs(
              nz - (n - 1));
          if (distanceToEnd < minDistance) {
            minDistance = distanceToEnd;
            closest[0] = nx;
            closest[1] = ny;
            closest[2] = nz;
          } else if (distanceToEnd == minDistance) {
            if (nx < closest[0] || (nx == closest[0] && ny < closest[1]) ||
                (nx == closest[0] && ny == closest[1] && nz < closest[2])) {
              closest[0] = nx;
              closest[1] = ny;
              closest[2] = nz;
            }
          }
        }
      }
    }

    return new Result(maxRemainingValue != -1, maxRemainingValue, bestPath,
        closest);
  }

  static class Result {
    boolean reachedEnd;
    int maxRemainingValue;
    List<int[]> path;
    int[] closest;

    Result(boolean reachedEnd, int maxRemainingValue, List<int[]> path,
        int[] closest) {
      this.reachedEnd = reachedEnd;
      this.maxRemainingValue = maxRemainingValue;
      this.path = path;
      this.closest = closest;
    }
  }
}
