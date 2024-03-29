import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

class FreqStack {//from leetcode
  Map<Integer, Integer> freq;//记录元素频率
  Map<Integer, Stack<Integer>> group;//包含对应频率的栈
  int maxfreq;

  public FreqStack() {
    freq = new HashMap();
    group = new HashMap();
    maxfreq = 0;
  }

  public void push(int x) {
    int f = freq.getOrDefault(x, 0) + 1;
    freq.put(x, f);//更新数字频率
    if (f > maxfreq)
      maxfreq = f;
    group.computeIfAbsent(f, z-> new Stack()).push(x);
  }

  public int pop() {
    int x = group.get(maxfreq).pop();
    freq.put(x, freq.get(x) - 1);
    if (group.get(maxfreq).size() == 0)
      maxfreq--;
    return x;
  }
}
