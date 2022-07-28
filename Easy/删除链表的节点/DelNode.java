package Easy.删除链表的节点;

public class DelNode {
    public ListNode deleteNode(ListNode head, int val) {
        ListNode ans = head;
        if (head.val==val){
            ans = head.next;
            head = null;
            return ans;
        }
        if (head.next!=null) {
            head = head.next;
        }
        while (head.next!=null){
            if (head.next.val==val){
                head.next = head.next.next;
                break;
            }
            head = head.next;
        }
        return ans;
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
