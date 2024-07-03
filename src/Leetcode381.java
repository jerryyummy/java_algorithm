import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

public class Leetcode381 {
  ArrayList<Integer> lst;
  HashMap<Integer, Set<Integer>> idx;
  java.util.Random rand = new java.util.Random();
  /** Initialize your data structure here. */

  public Leetcode381() {
    lst = new ArrayList<Integer>();
    idx = new HashMap<Integer, Set<Integer>>();
  }

  /** Inserts a value to the collection.
   * Returns true if the collection did not already contain the specified element.
   * */
  public boolean insert(int val) {
    if (!idx.containsKey(val)) idx.put(val, new LinkedHashSet<Integer>());
    idx.get(val).add(lst.size());
    lst.add(val);
    return idx.get(val).size() == 1;
  }

  /** Removes a value from the collection.
   * Returns true if the collection contained the specified element.
   * */
  public boolean remove(int val) {
    if (!idx.containsKey(val) || idx.get(val).isEmpty()) return false;
    int remove_idx = idx.get(val).iterator().next();//获取要删除元素的对应索引
    idx.get(val).remove(remove_idx);//从map中删除对应元素的这个索引
    int last = lst.get(lst.size() - 1);//获得最后一个位置的元素
    lst.set(remove_idx, last);//将插入列表中被删除位置设置为最后一个位置的元素
    idx.get(last).add(remove_idx);//将最后一个元素添加该索引位置
    idx.get(last).remove(lst.size() - 1);//把原先最后一个位置的索引删除

    lst.remove(lst.size() - 1);//移除最后一个元素
    return true;
  }

  /** Get a random element from the collection. */
  public int getRandom() {
    return lst.get(rand.nextInt(lst.size()));
  }
}
