public class Leetcode142 {
//  public ListNode sortList(ListNode head) {
//    if (head == null || head.next == null)
//      return head;
//    ListNode fast = head.next, slow = head;
//    while (fast != null && fast.next != null) {
//      slow = slow.next;
//      fast = fast.next.next;
//    }
//    ListNode tmp = slow.next;
//    slow.next = null;
//    ListNode left = sortList(head);
//    ListNode right = sortList(tmp);
//    ListNode h = new ListNode(0);
//    ListNode res = h;
//    while (left != null && right != null) {
//      if (left.val < right.val) {
//        h.next = left;
//        left = left.next;
//      } else {
//        h.next = right;
//        right = right.next;
//      }
//      h = h.next;
//    }
//    h.next = left != null ? left : right;
//    return res.next;
//  }

  public ListNode sortList(ListNode head) {
    if (head == null) {
      return head;
    }
    int length = 0;
    ListNode node = head;
    while (node != null) {
      length++;
      node = node.next;
    }
    ListNode dummyHead = new ListNode(0, head);
    for (int subLength = 1; subLength < length; subLength <<= 1) {
      ListNode prev = dummyHead, curr = dummyHead.next;
      while (curr != null) {
        ListNode head1 = curr;
        for (int i = 1; i < subLength && curr.next != null; i++) {
          curr = curr.next;
        }
        ListNode head2 = curr.next;
        curr.next = null;
        curr = head2;
        for (int i = 1; i < subLength && curr != null && curr.next != null; i++) {
          curr = curr.next;
        }
        ListNode next = null;
        if (curr != null) {
          next = curr.next;
          curr.next = null;
        }
        ListNode merged = merge(head1, head2);
        prev.next = merged;
        while (prev.next != null) {
          prev = prev.next;
        }
        curr = next;
      }
    }
    return dummyHead.next;
  }

  public ListNode merge(ListNode head1, ListNode head2) {
    ListNode dummyHead = new ListNode(0);
    ListNode temp = dummyHead, temp1 = head1, temp2 = head2;
    while (temp1 != null && temp2 != null) {
      if (temp1.val <= temp2.val) {
        temp.next = temp1;
        temp1 = temp1.next;
      } else {
        temp.next = temp2;
        temp2 = temp2.next;
      }
      temp = temp.next;
    }
    if (temp1 != null) {
      temp.next = temp1;
    } else if (temp2 != null) {
      temp.next = temp2;
    }
    return dummyHead.next;
  }
}
