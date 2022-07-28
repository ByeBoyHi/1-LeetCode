package Easy.从尾到头打印链表;

public class FromTailToHead {
    public int[] reversePrint(ListNode head) {
        int len = 0;
        ListNode t = head;
        while (t!=null){
            len++;
            t = t.next;
        }
        int[] ans = new int[len];
        while (head!=null){
            len--;
            ans[len] = head.val;
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