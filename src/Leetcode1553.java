import java.util.*;

public class Leetcode1553 {
  public int minDays(int n) {
    Queue<Integer> queue = new ArrayDeque<>();
    Set<Integer> visited = new HashSet<>();
    queue.add(n);
    int steps = 0;
    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        int oranges = queue.remove();
        if (oranges == 0) return steps;
        if (visited.contains(oranges)) continue;
        visited.add(oranges);
        for (int child: getChildren(oranges)) {
          if (!visited.contains(child))
            queue.add(child);
        }
      }
      steps++;
    }
    return -1;
  }

  private List<Integer> getChildren(int oranges) {
    List<Integer> children = new ArrayList<>();
    children.add(oranges-1);
    if (oranges % 2 == 0) children.add(oranges - (oranges / 2));
    if (oranges % 3 == 0) children.add(oranges - (2 * (oranges / 3)));
    return children;
  }

  /*
  static HashMap<Integer,Integer> hp;

    static int helper(int n){  // Top down

        if(n<=0) return 0;

        if(hp.containsKey(n)) return hp.get(n);

        int a = Integer.MAX_VALUE;
        int b = Integer.MAX_VALUE;
        int c = Integer.MAX_VALUE;

        if(n%2!=0 || n%3!=0) a = 1+helper(n-1);
        if(n%2==0) b = 1+helper(n/2);
        if(n%3==0) c = 1+helper(n/3);

        int ans = Math.min(a,Math.min(b,c));
        hp.put(n,ans);
        return ans;
    }
    public int minDays(int n) {
        hp = new HashMap<>();
        return helper(n);

    }
   */
}
