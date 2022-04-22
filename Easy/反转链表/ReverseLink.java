package Easy.反转链表;

import java.util.Stack;

public class ReverseLink {
    // 用栈
    public ListNode reverseList(ListNode head) {
        if (head==null) return null;
        Stack<ListNode> stack = new Stack<>();
        while (head!=null){
            stack.push(head);
            head = head.next;
        }

        ListNode cur = new ListNode();
        ListNode ans = cur;
        while (!stack.isEmpty()){
            cur.next = stack.pop();
            cur = cur.next;
            cur.next = null;
        }
        return ans.next;
    }

    // img_2
    public ListNode reverseList2(ListNode head) {
        if (head==null || head.next==null) return head;
        ListNode ans = head;
        head = head.next;
        ans.next = null;
        while (head!=null){
            ListNode nextHead = head.next;
            ListNode prev = head;
            prev.next = ans;
            ans = prev;
            head = nextHead;
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
