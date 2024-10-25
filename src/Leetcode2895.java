import java.util.Collections;
import java.util.List;

class Leetcode2895 {
  public int minProcessingTime(List<Integer> processorTime, List<Integer> tasks) {
    int n = processorTime.size();
    Collections.sort(processorTime);
    Collections.sort(tasks);
    Collections.reverse(tasks);

    int time = 0;
    int round = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 4*round; j < 4*round+4; j++) {
        int temp = processorTime.get(i) + tasks.get(j);
        time = Math.max(time, temp);
      }
      round++;
    }

    return time;
  }
}
