package Easy.两两交换链表中的节点;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }

    @Override
    public String toString() {
        return "val=" + val + "\n" + (next==null? "":next);
    }
}

class Solution {

    public static void main(String[] args){
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        swapPairs(node1);

        System.out.println(node1);
    }


    public static ListNode swapPairs(ListNode head) {
        ListNode node = head;
        ListNode node1 = head;
        while (node != null && node.next != null){
           int temp = node.val;
           node.val = node.next.val;
           node.next.val = temp;
           node = node.next.next;
        }
        return node1;
    }
}
