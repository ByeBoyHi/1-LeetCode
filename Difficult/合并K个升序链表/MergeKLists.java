package Difficult.合并K个升序链表;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MergeKLists {

    // 6ms
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode ans = new ListNode();
        ListNode list = ans;
        List<Integer> arr = new ArrayList<>();
        for (ListNode nodes: lists){
            while (nodes!=null){
                arr.add(nodes.val);
                nodes = nodes.next;
            }
        }

        Collections.sort(arr);
        for (int i: arr){
            ListNode cur = new ListNode(i);
            list.next = cur;
            list = cur;
        }

        return ans.next;
    }

    // 100ms
    public ListNode mergeKLists2(ListNode[] lists){
        ListNode ans = new ListNode();
        for (ListNode list : lists) {
            ans = mergeTwo(ans, list);
        }
        return ans;
    }

    // 两表合并
    public ListNode mergeTwo(ListNode a, ListNode b){
        ListNode head = new ListNode();
        ListNode move = head;
        while (a!=null && b!=null){
            if (a.val<b.val){
                move.next = a;
                move = move.next;
                a = a.next;
            }else {
                move.next = b;
                move = move.next;
                b = b.next;
            }
        }
        move.next = a==null?b:a;
        return head.next;
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
