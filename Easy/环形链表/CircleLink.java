package Easy.环形链表;

public class CircleLink {
    // 快慢指针
    public boolean hasCycle(ListNode head) {
        if (head==null) return false;
        ListNode fast = head, slow = head;
        while (fast.next!=null && fast.next.next!=null){
            fast = fast.next.next;
            slow = slow.next;
            if (fast==slow) return true;
        }
        return false;
    }
}

class ListNode{
    int val;
    ListNode next;
    public ListNode(int val){
        this.val = val;
        next = null;
    }
}
