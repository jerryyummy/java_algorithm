package citadel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StableSegments {
  public static int getStableSegments(List<Integer> capacity){
    int n = capacity.size();
    int[] prefix = new int[n];
    prefix[0] = capacity.get(0);
    for (int i = 1; i < n; i++) {
      prefix[i] = capacity.get(i)+prefix[i-1];
    }

    int res = 0;
    HashMap<Integer, List<Integer>> map = new HashMap<>();
    for (int i = 0; i < n; i++) {
      int number = capacity.get(i);
      if (!map.containsKey(number)) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(i);
        map.put(number, list);
      }else{
        for(int index: map.get(number)){
          if(i-index>=2 && prefix[i-1]-prefix[index] == number) res++;
        }
        map.get(number).add(i);
      }
    }
    return res;
  }

  public static void main(String[] args) {
    List<Integer> capacity = new ArrayList<Integer>();
    capacity.add(8);
    capacity.add(2);
    capacity.add(4);
    capacity.add(2);
    capacity.add(8);
    System.out.println(getStableSegments(capacity));
  }
}
