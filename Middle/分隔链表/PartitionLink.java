package Middle.分隔链表;

public class PartitionLink {

    public static void main(String[] args) {
        ListNode head = new ListNode(5);
        head.next = new ListNode(6);
        head.next.next = new ListNode(4);
        head.next.next.next = new ListNode(1);
        System.out.println(new PartitionLink().partition(head, 4).val);
    }

    public ListNode partition(ListNode head, int x) {
        ListNode ans = new ListNode();
        ListNode big = new ListNode();
        ListNode small = new ListNode();
        ans.next = small;
        ListNode tmp = big;
        while (head!=null){
            if (head.val<x){
                small.next = new ListNode(head.val);
                small = small.next;
            }else {
                big.next = new ListNode(head.val);
                big = big.next;
            }
            head = head.next;
        }
        small.next = tmp.next;
        return ans.next.next;
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
