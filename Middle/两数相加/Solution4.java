package Middle.两数相加;

public class Solution4 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode pre = new ListNode(0);
        ListNode ans = pre;
        while (l1 != null && l2 != null) {
            ListNode cur = new ListNode(l1.val + l2.val);
            if (pre.val >= 10) {
                pre.val -= 10;
                cur.val++;
            }
            pre.next = cur;
            pre = cur;
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1!=null){
            ListNode cur = new ListNode(l1.val);
            if (pre.val >= 10) {
                pre.val -= 10;
                cur.val++;
            }
            pre.next = cur;
            pre = cur;
            l1 = l1.next;
        }
        while (l2!=null){
            ListNode cur = new ListNode(l2.val);
            if (pre.val >= 10) {
                pre.val -= 10;
                cur.val++;
            }
            pre.next = cur;
            pre = cur;
            l2 = l2.next;
        }
        if (pre.val>=10){
            pre.next = new ListNode(1);
            pre.val-=10;
        }
        return ans.next;
    }
}
