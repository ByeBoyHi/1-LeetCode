package Middle.反转链表2;

public class RevLink2 {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left==right || head.next==null) return head;
        int x = 1;
        ListNode p = new ListNode();
        p.next = head;
        while (x<left){
            x++;
            p = p.next;
        }
        p.next = reverse(p.next, right-left);
        if (left==1) return p.next;  // 如果从第一个就要开始翻转，那么新翻转的头就是我们这个链表的头部
        return head;
    }

    public ListNode reverse(ListNode head, int k){
        ListNode ans = head;
        ListNode end = ans;
        head = head.next;
        ans.next = null;
        while (k>0){
            ListNode nextHead = head.next;
            head.next = ans;
            ans = head;
            k--;
            head = nextHead;
        }
        end.next = head;
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

