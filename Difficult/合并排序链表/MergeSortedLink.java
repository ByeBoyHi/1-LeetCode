package Difficult.合并排序链表;

import java.util.PriorityQueue;

public class MergeSortedLink {

    // 104ms：朴素二合一
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length==0) return null;
        ListNode ans = null;
        for (ListNode list: lists){
            ans = merge(ans, list);
        }
        return ans;
    }

    // 两个链表的合并
    public ListNode merge(ListNode a, ListNode b){
        if(a == null && b == null){
            return null;
        }
        if(a == null){
            return b;
        }
        if(b == null){
            return a;
        }
        ListNode ans = new ListNode();
        ListNode t = ans;
        while (a!=null && b!=null){
            if (a.val<b.val){
                t.next = a;
                t = a;
                a = a.next;
            }else {
                t.next = b;
                t = b;
                b = b.next;
            }
        }
        if (a!=null){
            t.next = a;
        }
        if (b!=null){
            t.next = b;
        }
        return ans.next;
    }

    // 1ms
    public ListNode mergeKLists3(ListNode[] lists) {
        if(lists == null || lists.length == 0){
            return null;
        }
        return process(lists,0,lists.length-1);
    }

    // 利用归并的思想进行合并
    public ListNode process(ListNode[] lists,int l,int r) {
        if(l >= r){
            return lists[l];
        }
        int mid = (r+l) / 2;
        ListNode left = process(lists,l,mid);
        ListNode right = process(lists,mid+1,r);
        return recersionMerge(left,right);
    }

    public static ListNode recersionMerge(ListNode head1,ListNode head2){
        if(head1 == null && head2 == null){
            return null;
        }
        if(head1 == null){
            return head2;
        }
        if(head2 == null){
            return head1;
        }
        ListNode result = new ListNode(0);
        ListNode cur = result;
        while (head1 != null && head2 != null){
            if(head1.val <= head2.val){
                ListNode next = head1.next;
                cur.next = head1;
                head1.next = null;
                cur = cur.next;
                head1 = next;
            }else {
                ListNode next = head2.next;
                cur.next = head2;
                head2.next = null;
                cur = cur.next;
                head2 = next;
            }
        }
        while (head1 != null){
            ListNode next = head1.next;
            cur.next = head1;
            head1.next = null;
            cur = cur.next;
            head1 = next;
        }
        while (head2 != null){
            ListNode next = head2.next;

            cur.next = head2;
            head2.next = null;

            cur = cur.next;
            head2 = next;
        }
        ListNode ret = result.next;
        result.next = null;
        return ret;
    }

    // 5ms：优先队列
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists.length==0) return null;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (ListNode list: lists){
            while (list!=null){
                queue.add(list.val);
                list = list.next;
            }
        }
        ListNode ans = new ListNode();
        ListNode t = ans;

        while (!queue.isEmpty()){
            ListNode cur = new ListNode(queue.poll());
            t.next = cur;
            t = cur;
        }

        return ans.next;
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

