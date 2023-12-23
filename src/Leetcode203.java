public class Leetcode203 {
    public ListNode removeElements(ListNode head, int val) {
        ListNode p = null, q = new ListNode();
        q.next = head;
        p = q;
        while (head!=null){
            ListNode next = head.next;
            if (head.val==val){
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
