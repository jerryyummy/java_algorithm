import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Leetcode465 {
  public int minTransfers(int[][] transactions) {
    HashMap<Integer,Integer> records = new HashMap<>();
    for(int[] transaction:transactions){
      int from = transaction[0];
      int to = transaction[1];
      int amount = transaction[2];
      records.put(from,records.getOrDefault(from,0)-amount);
      records.put(to,records.getOrDefault(to,0)+amount);
    }
    List<Integer> list = new ArrayList<>();
    for (Map.Entry<Integer,Integer> entry:records.entrySet()){
      if (entry.getValue()!=0){
        list.add(entry.getValue());
      }
    }
    return dfs(0,list);
  }

  public int dfs(int k, List<Integer> list){
    if (k==list.size()) return 0;
    int cur = list.get(k);
    if (cur==0) return dfs(k+1,list);
    int min = Integer.MAX_VALUE;
    for (int i = k+1; i < list.size(); i++) {
      int next = list.get(i);
      if (cur*next<0){//如果两个人之间一个收钱一个欠钱
        list.set(i,cur+next);
        min = Math.min(min,dfs(k+1,list)+1);
        list.set(i,next);
      }
    }
    return min;
  }
}
