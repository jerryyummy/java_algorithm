class MyLinkedList {

    class ListNode {
        int val;
        ListNode next;
        ListNode prev;
        ListNode(int val) { this.val = val; }
    }
    ListNode head;
    ListNode tail;
    int size;

    public MyLinkedList() {
        head = new ListNode(0); // 哑元
        tail = new ListNode(0); // 哑元
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    public int get(int index) {
        if (index < 0 || index >= size) return -1;   // 0-based
        ListNode cur = head.next;
        for (int i = 0; i < index; i++) cur = cur.next;
        return cur.val;
    }

    public void addAtHead(int val) {
        ListNode newNode = new ListNode(val);
        ListNode next = head.next;
        head.next = newNode;
        newNode.prev = head;
        newNode.next = next;
        next.prev = newNode;
        size++;
    }

    public void addAtTail(int val) {
        ListNode newNode = new ListNode(val);
        ListNode prev = tail.prev;
        prev.next = newNode;
        newNode.prev = prev;
        newNode.next = tail;
        tail.prev = newNode;
        size++;
    }

    public void addAtIndex(int index, int val) {
        if (index <= 0) {                    // 负数按 0 处理
            addAtHead(val);
            return;
        }
        if (index == size) {                 // 等于 size 在尾部加
            addAtTail(val);
            return;
        }
        if (index > size) return;            // 超界，忽略

        // 找到第 index 个节点（将把新节点插到它前面）
        ListNode cur = head.next;
        for (int i = 0; i < index; i++) cur = cur.next;

        ListNode prev = cur.prev;
        ListNode newNode = new ListNode(val);
        prev.next = newNode;
        newNode.prev = prev;
        newNode.next = cur;
        cur.prev = newNode;
        size++;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) return;

        // 定位到第 index 个节点并删除
        ListNode cur = head.next;
        for (int i = 0; i < index; i++) cur = cur.next;

        ListNode prev = cur.prev, next = cur.next;
        prev.next = next;
        next.prev = prev;
        size--;
    }
}
