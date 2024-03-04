public class Leetcode23 {
  public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
    if (list1 == null) {
      return list2;
    }
    else if (list2 == null) {
      return list1;
    }
    else if (list1.val < list2.val) {
      list1.next = mergeTwoLists(list1.next, list2);
      return list1;
    }
    else {
      list2.next = mergeTwoLists(list1, list2.next);
      return list2;
    }
  }

  public ListNode mergeKLists(ListNode[] lists){
    if(lists==null || lists.length==0) return null;
    return merge(lists,0,lists.length-1);
  }

  public ListNode merge(ListNode[] lists, int left, int right){
    if (left==right) return lists[left];
    else {
      int mid = (left+right)/2;
      ListNode node1 = merge(lists,left,mid);
      ListNode node2 = merge(lists,mid+1,right);
      return mergeTwoLists(node1,node2);
    }
  }

}
