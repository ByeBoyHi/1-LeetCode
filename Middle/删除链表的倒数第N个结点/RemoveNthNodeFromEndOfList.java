package Middle.删除链表的倒数第N个结点;

import java.util.ArrayList;
import java.util.List;

public class RemoveNthNodeFromEndOfList {
    static class ListNode{
        int val;
        ListNode next;

        public ListNode(){}

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    // 暴力
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head==null){
            return null;
        }

        int len = 0;
        ListNode ans = head;
        while (head!=null){
            len++;
            head = head.next;
        }

        if (len==n){  // 要删除第一个节点
            ListNode res = ans.next;
            ans = null;
            return res;
        }

        ListNode del = ans;
        while (len-1>n){
            del = del.next;
            len--;
        }
        ListNode next = del.next;
        del.next = next.next;
        next = null;
        return ans;
    }

    // 用一个列表存了一下
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        if (head==null || head.next==null){
            return null;
        }

        // 至少两个节点
        List<ListNode> list = new ArrayList<>();
        ListNode ans = head;
        while (head!=null){
            list.add(head);
            head = head.next;
        }
        int len = list.size();
        if (len==n){  // 删除第一个节点
            ListNode res = list.get(0);
            res = null;
            return list.get(1);
        }

        ListNode pre = list.get(len-n-1);
        ListNode next = list.get(len-n);
        pre.next = next.next;
        next = null;

        return ans;
    }

    // 快慢指针
    /*
        先让快指针先走n步，那么此时快慢指针之间差n-1
        这之后让快慢指针同时一步一步走，当快指针走到尾部的时候，慢指针就在要删除节点的前一个
     */
    public ListNode removeNthFromEnd3(ListNode head, int n) {
        if(head==null || head.next==null){
            return null;
        }
        // 用dummyHead之后，相当于在head前面还有一个Head，就避免了n=len的时候，快指针越界的问题
        ListNode dummyHead = new ListNode(0, head);
        ListNode fast = dummyHead;
        ListNode slow = dummyHead;
        int num = 0;
        while (num<n){
            fast = fast.next;
            num++;
        }
        while (fast.next!=null){
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;

        return dummyHead.next;
    }
}
