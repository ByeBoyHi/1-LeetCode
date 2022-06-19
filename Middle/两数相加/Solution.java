package Middle.两数相加;

import java.util.HashMap;

/**
 * 给出两个非空 的链表用来表示两个非负的整数。
 * 其中，它们各自的位数是按照逆序的方式存储的，并且它们的每个节点只能存储一位数字。
 * <p>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p>
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * ===============================================================
 * 示例：
 * 输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 8 -> 0 -> 7
 * 原因：7243 + 564 = 7807
 *
 * @author 唐
 * @date 2020/10/14 0014
 * @time 21:02
 */
public class Solution {

    public static void main(String[] args) {
        ListNode list1 = new ListNode(7);
        ListNode list11 = new ListNode(2);
        ListNode list111 = new ListNode(4);
        ListNode list1111 = new ListNode(3);
        list1.next = list11;
        list11.next = list111;
        list111.next = list1111;

        ListNode list2 = new ListNode(5);
        ListNode list22 = new ListNode(6);
        ListNode list222 = new ListNode(4);
        list2.next = list22;
        list22.next = list222;

        ListNode lst = addTwoNumbers(list1, list2);
        System.out.println(lst);
    }

    private static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) return null;
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode tmp = new ListNode();
        ListNode res;
        int len1 = Len(l1);
        int len2 = Len(l2);
        res = f(len1, len2, l1, l2, tmp);
        return res;
    }

    public static ListNode f(int len1, int len2, ListNode l1, ListNode l2, ListNode tmp){
        int index;
        ListNode temp;
        ListNode res;
        int flag;
        ListNode l;

        if (len1<len2){
            index = len2-len1;
            temp = l2;
            res = l2;
            flag = len1;
            l = l1;
        }else{
            index = len1-len2;
            temp = l1;
            res = l1;
            flag = len2;
            l = l2;
        }

        ListNode head = res;

        while (index-- > 0){
            temp = temp.next;
        }
        for (int i=0; i<Math.abs(len1-len2)-1; i++){
            res = res.next;
        }
        for (int i=0; i<flag; i++){
            tmp.val = temp.val + l.val;
            if (0==len1-len2)
                head = tmp;
            System.out.println(tmp.val);
            res.next = tmp;
            tmp = tmp.next;
            res = res.next;
            l = l.next;
        }

        return head;
    }

    public static int Len(ListNode listNode){
        ListNode temp = listNode;
        int len = 0;
        while (temp!=null){
            len++;
            temp = temp.next;
        }
        return len;
    }
}


class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        return val + (next == null ? "" : "->" + next);
    }
}