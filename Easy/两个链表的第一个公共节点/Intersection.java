package Easy.两个链表的第一个公共节点;

public class Intersection {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode A = headA;
        ListNode B = headB;
        int a = 0;
        int b = 0;
        while (A!=null){
            a++;
            A = A.next;
        }
        while (B != null) {
            b++;
            B = B.next;
        }
        int diff = Math.abs(a-b);
        A = headA;
        B = headB;
        if (a>b){
            while (diff>0){
                A = A.next;
                diff--;
            }
        }
        if (a<b){
            while (diff>0){
                B = B.next;
                diff--;
            }
        }

        while (A!=null && B!=null){
            if (A==B){
                return A;
            }
            A = A.next;
            B = B.next;
        }
        return null;
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
