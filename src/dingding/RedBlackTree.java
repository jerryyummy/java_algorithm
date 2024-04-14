package dingding;

import java.util.*;

public class RedBlackTree {
  public static void main(String[] strs) {
    //钉钉3.23第三题
    //一棵n个节点的树，起始所有节点都是B黑色。根节点为1
    //将一些节点染为R红色，要求任意一棵子树中R红色节点的总数不是3的倍数（0也是三的倍数）
    //输入任意一种染色方案


    //想一下怎么做？
    //每个节点都可以选择染为红色还是黑色，那么如果dfs搜索的话，
    //如果一个节点的所有子树都满足，但是到这个节点这里就不满足了，那么只需要将这个节点的颜色翻转。
    //那么从下往上，就这样依次变化呗
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    Map<Integer, List<Integer>> mp = new HashMap<>();
    for (int i = 1; i <= n; i++) {
      mp.put(i, new ArrayList<>());
    }
    boolean[] red = new boolean[n + 1];
    for (int i = 1; i <= n - 1; i++) {
      int u = in.nextInt();
      int v = in.nextInt();
      List<Integer> list1 = mp.get(u);
      List<Integer> list2 = mp.get(v);
      list1.add(v);
      list2.add(u);
      mp.put(u, list1);
      mp.put(v, list2);
    }
    help0(1, mp);
    help1(1, mp, red);
    for (int i = 1; i <= n; i++) {
      if (red[i]) {
        System.out.print("R");
      } else {
        System.out.print("W");
      }
    }
  }


  public static void help0(int index, Map<Integer, List<Integer>> mp) { //从根节点向下遍历，去除子节点指向父节点的边
    List<Integer> children = mp.get(index);
    for (Integer child : children) {
      List<Integer> tmp = mp.get(child);
      tmp.remove(Integer.valueOf(index)); //树，单向，去掉子节点指向父节点的边
      help0(child, mp);
    }
  }




  // dfs搜索就行, 起始所有节点为黑色
  // 就是对于某一节点的一棵树，其子树都满足了，获取所有子树的红色节点之和count，如果count % 3 != 0,那么当前节点颜色不用变化
  // 否则就将其染为黑色
  public static int help1(int index, Map<Integer, List<Integer>> mp, boolean[] red) {
    List<Integer> children = mp.get(index);
    int count = 0;
    for (Integer child : children) {
      count += help1(child, mp, red);
    }
    if (count % 3 == 0) {
      red[index] = true; //起始都是黑色，如果不满足的话染成红色
      count += 1;
    }
    return count;

  }

}
