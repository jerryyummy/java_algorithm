import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Leetcode433 {
  public int minMutation(String startGene, String endGene, String[] bank) {
    HashSet<String> validGene = new HashSet<>(Arrays.asList(bank));
    Queue<String> queue = new LinkedList<>();
    String[] genes = new String[]{"A", "T", "C", "G"};
    HashSet<String> visited = new HashSet<>();
    int step = 0;
    queue.offer(startGene);
    visited.add(startGene);

    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int s = 0; s < size; s++) {
        String cur = queue.poll();
        if (cur.equals(endGene)) return step;

        char[] curArray = cur.toCharArray();
        for (int i = 0; i < curArray.length; i++) {
          char old = curArray[i];
          for (int j = 0; j < genes.length; j++) {
            curArray[i] = genes[j].charAt(0);
            String next = new String(curArray);
            if (!visited.contains(next) && validGene.contains(next)) {
              queue.offer(next);
              visited.add(next);
            }
          }
          curArray[i] = old;
        }
      }
      step++;
    }
    return -1;
  }


  public static void main(String[] args) {
    Leetcode433 solution = new Leetcode433();
    solution.minMutation("AACCGGTT", "AACCGGTA", new String[]{"AACCGGTA"});
  }

}
