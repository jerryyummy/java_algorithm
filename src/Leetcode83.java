public class Leetcode83 {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode p = null, q = new ListNode(101);
        q.next = head;
        p = q;
        while (head!=null){
            ListNode next = head.next;
            if (head.val== q.val){
                q.next = next;
                head = next;
            }else{
                q = head;
                head = next;
            }
        }
        return p.next;
    }
}
