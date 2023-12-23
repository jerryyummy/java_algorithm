import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

public class Leetcode234 {
    public boolean isPalindrome(ListNode head) {
        Deque<Integer> deque = new ArrayDeque<>();
        while (head!=null){
            deque.push(head.val);
            head = head.next;
        }
        while (deque.size()>1){
            Integer node1 = deque.pollFirst();
            Integer node2 = deque.pollLast();
            if (!Objects.equals(node2, node1)) return false;
        }
        return true;
    }
}
