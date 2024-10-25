public class Leetcode237 {
  public void deleteNode(ListNode node) {
    ListNode cur = node;
    ListNode pre = null;
    while (cur.next != null) {
      pre = cur;
      ListNode next = cur.next;
      cur.val = next.val;
      cur = next;
    }
    pre.next = null;
  }
}
