import java.util.HashMap;
import java.util.PriorityQueue;

public class Leetcode1387 {
  HashMap<Integer, Integer> cache;
  public int getKth(int lo, int hi, int k) {
    cache = new HashMap<>();
    PriorityQueue<Item> maxHeap = new PriorityQueue<Item>((a, b) -> (a.power == b.power) ? (b.num - a.num) : (b.power - a.power));

    for(int num = lo; num <= hi; num++){
      maxHeap.add(new Item(num, getPower(num)));
      if(maxHeap.size() > k){
        maxHeap.remove();
      }
    }

    return maxHeap.remove().num;
  }

  private int getPower(int n){
    if(n == 1) return 0;

    if(cache.containsKey(n)) return cache.get(n);

    int power = 1 + ((n % 2 == 0) ?  getPower(n / 2) : getPower((3 * n) + 1));

    cache.put(n, power);
    return power;
  }
}

class Item {
  int num;
  int power;

  public Item (int num, int power){
    this.num = num;
    this.power = power;
  }
}
