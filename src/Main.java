import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Main {

//  static final int MOD = 1000000007;第三题
//
//  private int countWays(ListNode node, int index, boolean isEven, Map<ListNode, Integer> evenMemo, Map<ListNode, Integer> oddMemo, String colors) {
//    // 如果当前节点为空，检查isEven是否为true，如果为true，返回1，否则返回0
//    if (node == null) return isEven ? 1 : 0;
//
//    // 检查是否已经计算过这个状态
//    if (isEven && evenMemo.containsKey(node)) {
//      return evenMemo.get(node);
//    } else if (!isEven && oddMemo.containsKey(node)) {
//      return oddMemo.get(node);
//    }
//
//    int ways = 0;
//
//    // 当前节点是否已被染成红色
//    boolean isRed = colors.charAt(index) == 'R';
//
//    // 如果当前节点已经是红色的
//    if (isRed) {
//      // 如果当前节点权值是偶数，状态不变，否则状态翻转
//      ways = countWays(node.next, index+1, node.val % 2 == 0 ? isEven : !isEven, evenMemo, oddMemo, colors);
//    } else {
//      // 如果当前节点是白色的，我们可以选择不染色
//      ways += countWays(node.next, index+1, isEven, evenMemo, oddMemo, colors);
//      ways %= MOD;
//      // 也可以选择染色，如果权值是偶数，状态不变，否则状态翻转
//      ways += countWays(node.next, index+1, node.val % 2 == 0 ? isEven : !isEven, evenMemo, oddMemo, colors);
//      ways %= MOD;
//    }
//
//    // 存储这个状态的结果
//    if (isEven) {
//      evenMemo.put(node, ways);
//    } else {
//      oddMemo.put(node, ways);
//    }
//
//    return ways;
//  }
//
//  public int getMethod(ListNode head, String colors) {
//    Map<ListNode, Integer> evenMemo = new HashMap<>();
//    Map<ListNode, Integer> oddMemo = new HashMap<>();
//    return countWays(head, 0,  true, evenMemo, oddMemo, colors); // 初始为true，因为空的权值和为0（偶数）
//  }

  public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param s string字符串
     * @param k int整型
     * @return int整型
     */
    public int maxLen(String s, int k) {
      if(k>=s.length() || s.length()==0) return 0;

      int left = 0, right = 0;
      int currentLength = 0;

      for (int i = 0; i < s.length(); i++) {
        if (s.charAt(i) == '1') {
          currentLength++;
        } else {
          if (currentLength > right) {
            right = currentLength;
            currentLength = 0;
          }
        }
      }
      if (currentLength > right) {
        right = currentLength;
      }


      while (left < right) {
        int mid = left + (right - left) / 2;
        if (canAchieve(s, k, mid)) {
          right = mid;
        } else {
          left = mid + 1;
        }
      }

      return left;
    }

    private boolean canAchieve(String s, int k, int maxLen) {
      int i = 0, count = 0, modifications = 0;

      while (i < s.length()) {
        int start = i;
        while (i < s.length() && s.charAt(i) == '1') {
          i++;
        }
        int len = i - start;
        if (len > maxLen) {

          int fullSections = len / (maxLen + 1);
          modifications += fullSections;
          if (fullSections * (maxLen + 1) < len) {
            modifications++;
          }
        }
        i++;
      }

      return modifications <= k;
    }

  }

}

