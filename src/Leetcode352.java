import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


public class Leetcode352 {
  TreeMap<Integer,Integer> map ;
  Set<Integer> set;
  public Leetcode352() {
    map = new TreeMap<>();
    set = new HashSet<>();
  }

  public void addNum(int value) {
    if (set.contains(value)) return;
    else if (set.contains(value-1) && set.contains(value+1)){
      int end = map.get(value+1);
      map.remove(value+1);
      int start = map.floorKey(value);
      map.put(start,end);
    }else if (set.contains(value-1)){
      int start = map.floorKey(value);
      map.put(start,value);
    }else if (set.contains(value+1)){
      int end = map.get(value+1);
      map.remove(value+1);
      map.put(value,end);
    }else{
      map.put(value,value);
    }
    set.add(value);
  }

  public int[][] getIntervals() {
    int size = map.size();
    int[][] intervals = new int[size][2];
    int i = 0;
    for (Map.Entry<Integer,Integer> entry:map.entrySet()){
      intervals[i][0] = entry.getKey();
      intervals[i][1] = entry.getValue();
      i++;
    }
    return intervals;
  }

}
