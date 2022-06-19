package Easy.相交链表;

public class IntersectionLink {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode A = headA;
        ListNode B = headB;

        int idxA = 0, idxB = 0;
        while (headA!=null){
            idxA++;
            headA = headA.next;
        }
        while (headB!=null){
            idxB++;
            headB = headB.next;
        }
        while (idxA>idxB){
            idxA--;
            assert A != null;
            A = A.next;
        }
        while (idxB>idxA){
            idxB--;
            assert B != null;
            B = B.next;
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
