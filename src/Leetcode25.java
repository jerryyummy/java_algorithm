public class Leetcode25 {
  public ListNode reverseLinkedList(ListNode head, int k) {
    ListNode new_head = null;
    ListNode ptr = head;
    while (k > 0) {
      ListNode next_node = ptr.next;
      ptr.next = new_head;
      new_head = ptr;
      ptr = next_node;
      k--;
    }
    return new_head;

  }

  public ListNode reverseKGroup(ListNode head, int k) {
    int count = 0;
    ListNode ptr = head;

    while (count < k && ptr != null) {
      ptr = ptr.next;
      count++;
    }

    if (count == k) {
      ListNode reversedHead = this.reverseLinkedList(head, k);
      head.next = this.reverseKGroup(ptr, k);
      return reversedHead;
    }else{
      return head;
    }
  }

}
