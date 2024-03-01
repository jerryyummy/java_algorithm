import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Leetcode399 {
  public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
    HashMap<String, Integer> map = new HashMap<>();
    int index = 0;
    // Initialize character to position mapping
    for (List<String> equation : equations) {
      if (!map.containsKey(equation.get(0))) {
        map.put(equation.get(0), index++);
      }
      if (!map.containsKey(equation.get(1))) {
        map.put(equation.get(1), index++);
      }
    }

    // Store edges and their weights
    double[][] matrix = new double[map.size()][map.size()];
    for (int i = 0; i < matrix.length; i++) {
      Arrays.fill(matrix[i], -1.0); // Initialize with -1 for no direct connection
    }
    for (int i = 0; i < equations.size(); i++) {
      int position1 = map.get(equations.get(i).get(0));
      int position2 = map.get(equations.get(i).get(1));
      matrix[position1][position2] = values[i];
      matrix[position2][position1] = 1.0 / values[i];
    }

    double[] res = new double[queries.size()];
    for (int i = 0; i < queries.size(); i++) {
      List<String> query = queries.get(i);
      if (!map.containsKey(query.get(0)) || !map.containsKey(query.get(1))) {
        res[i] = -1.0;
      } else {
        int start = map.get(query.get(0));
        int end = map.get(query.get(1));
        res[i] = dfs(matrix, new int[matrix.length], start, end, 1.0);
      }
    }
    return res;
  }

  public double dfs(double[][] matrix, int[] visit, int start, int end, double cur) {
    if (start == end) return cur;
    visit[start] = 1;

    for (int i = 0; i < matrix[start].length; i++) {
      if (visit[i] == 0 && matrix[start][i] != -1) {
        double result = dfs(matrix, visit, i, end, cur * matrix[start][i]);
        if (result != -1.0) {
          return result;
        }
      }
    }

    visit[start] = 0; // Reset visit before returning
    return -1.0;
  }
}
