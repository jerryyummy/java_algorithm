public class Leetcode1669 {
  public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
    ListNode p = list1;
    ListNode q = list1;

    // Advance p by a-1 steps
    for (int i = 0; i < a - 1; i++) {
      p = p.next;
    }

    // Advance q by b+1 steps
    for (int i = 0; i <= b; i++) {
      q = q.next;
    }

    // Connect the end of list2 to the node after the range
    ListNode temp = list2;
    while (temp.next != null) {
      temp = temp.next;
    }

    // Merge the lists
    p.next = list2;
    temp.next = q;

    return list1;
  }
}
