package Easy.合并两个排序的链表;

public class MergeTwoLink {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode ans = new ListNode(0);
        ListNode move = ans;
        while (l1!=null && l2!=null){
            if (l1.val<l2.val){
                move.next = l1;
                move = l1;
                l1 = l1.next;
            }else{
                move.next= l2;
                move = l2;
                l2 = l2.next;
            }
        }
        if (l1!=null){
            move.next = l1;
        }
        if (l2!=null){
            move.next = l2;
        }
        return ans.next;
    }
}
class ListNode{
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }
}
