package Easy.链表的中间结点;

public class MiddleNode {
    // 暴力破解
    public ListNode middleNode(ListNode head) {
        int n=0;
        ListNode ans = head;
        while (head!=null){
            n++;
            head = head.next;
        }

        int idx = n/2;
        while (idx>0){
            ans = ans.next;
            idx--;
        }

        if ((n&1)==1){
            return ans;
        }

        assert ans != null;
        return ans.next;
    }

    // 快慢指针
    public ListNode middleNode2(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next!=null && fast.next.next!=null){
            fast = fast.next.next;
            slow = slow.next;
        }
        if (fast.next!=null){
            return slow.next;
        }
        return slow;
    }
}

class ListNode{
    int val;
    ListNode next;

    public ListNode() {}

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
