import java.util.*;

public class Main {
  public int minimumCost(String target, String[] words, int[] costs) {
    PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.cost-b.cost);
    for (int i = 0; i < words.length; i++) {
      if (words[i].equals(target.substring(0,words[i].length()))) {
        pq.offer(new Pair(costs[i], words[i]));
      }
    }

    while (!pq.isEmpty()) {
      Pair p = pq.poll();
      if (p.word.equals(target)){
        return p.cost;
      }
      int len = p.word.length();
      for (int i = 0; i < words.length; i++) {
        if (len+words[i].length() > target.length()) {
          continue;
        }
        String temp = p.word+words[i];
        if (temp.equals(target.substring(0, len+words[i].length()))){
          pq.offer(new Pair(costs[i]+p.cost, temp));
        }
      }
    }

    return -1;
  }
}

class Pair{
  int cost;
  String word;
  public Pair(int cost, String word) {
    this.cost = cost;
    this.word = word;
  }
}

