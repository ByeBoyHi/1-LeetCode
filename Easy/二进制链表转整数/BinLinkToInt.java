package Easy.二进制链表转整数;

public class BinLinkToInt {
    public int getDecimalValue(ListNode head) {
        int len = 0;
        ListNode t = head;
        while (t!=null){
            t = t.next;
            len++;
        }
        int ans = 0;
        while (head!=null) {
            len--;
            if (head.val == 1) {
                ans += (int) Math.pow(2, len) * head.val;
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
