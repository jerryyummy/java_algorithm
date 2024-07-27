import java.util.ArrayDeque;
import java.util.Deque;

public class Leetcode2487 {
  public ListNode removeNodes(ListNode head) {
    Deque<Integer> deque = new ArrayDeque<>();
    while (head != null) {
      deque.push(head.val);
      head = head.next;
    }
    ListNode dummy = null;
    while (!deque.isEmpty()) {
      int val = deque.pop();
      if (dummy == null) {
        dummy = new ListNode(val);
      }else if (val >= dummy.val){
        ListNode node = new ListNode(val);
        node.next = dummy;
        dummy = node;
      }
    }

    return dummy;
  }
}
