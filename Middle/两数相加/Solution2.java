package Middle.两数相加;

/**
 * 2ms代码
 *
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
public class Solution2 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null){
            return l2;
        }

        if(l2 == null){
            return l1;
        }

        int len1 = 0;
        ListNode tmp = l1;
        while(tmp != null){
            ++len1;
            tmp = tmp.next;
        }

        int len2 = 0;
        tmp = l2;
        while(tmp != null){
            ++len2;
            tmp = tmp.next;
        }

        ListNode res =  addWithLen(l1, len1, l2, len2);
        if(res != null && res.val > 9){
            ListNode node = new ListNode(1);
            res.val -= 10;
            node.next = res;
            res = node;
        }

        return res;
    }

    private ListNode addWithLen(ListNode l1, int len1, ListNode l2, int len2){
        if(len1 == 0 && len2 == 0){
            return null;
        }

        if(len1 > len2){
            ListNode cur = new ListNode(l1.val);
            ListNode nxt = addWithLen(l1.next, len1 - 1, l2, len2);
            if(nxt != null && nxt.val > 9){
                cur.val += 1;
                nxt.val -= 10;
            }
            cur.next = nxt;
            return cur;
        }

        if(len1 < len2){
            ListNode cur = new ListNode(l2.val);
            ListNode nxt = addWithLen(l1, len1, l2.next, len2 - 1);
            if(nxt != null && nxt.val > 9){
                cur.val += 1;
                nxt.val -= 10;
            }
            cur.next = nxt;
            return cur;
        }

        ListNode cur = new ListNode(l1.val + l2.val);
        ListNode nxt = addWithLen(l1.next, len1 - 1, l2.next, len2 - 1);
        if(nxt != null && nxt.val > 9){
            cur.val += 1;
            nxt.val -= 10;
        }
        cur.next = nxt;
        return cur;
    }
}
