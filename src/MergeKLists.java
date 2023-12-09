class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

public class MergeKLists {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length==0){
            return null;
        }
        return merge(lists,0,lists.length-1);
    }

        private ListNode merge(ListNode[] lists,int left,int right){
        if (left == right){
            return lists[left];
        }
        int mid = (right+left)/2;
        ListNode l1 = merge(lists,left,mid);
        ListNode l2 = merge(lists,mid+1,right);
        return mergeTwoLists(l1,l2);
    }

    private ListNode mergeTwoLists(ListNode l1, ListNode l2){
        if (l1 == null || l2 == null){
            return l1 == null?l2:l1;
        }
        ListNode head = new ListNode();
        ListNode cur = head;
        while( l1 != null && l2 != null){
            if (l1.val<l2.val){
                cur.next = l1;
                cur = cur.next;
                l1 = l1.next;
            }else {
                cur.next = l2;
                cur = cur.next;
                l2 = l2.next;
            }
        }
        if (l1!=null){
            cur.next = l1;
        }
        if (l2!=null){
            cur.next = l2;
        }
        return head.next;
    }
}
