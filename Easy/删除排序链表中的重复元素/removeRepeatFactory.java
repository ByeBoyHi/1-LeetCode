package Easy.删除排序链表中的重复元素;

public class removeRepeatFactory {
    public ListNode deleteDuplicates(ListNode head) {
        if (head==null){
            return head;
        }
        ListNode node = head;
        while (node.next!=null){
            if (node.next.val!= node.val){
                node = node.next;
            }else {
                ListNode tmp = node.next;
                while (tmp!=null&&tmp.val==node.val){
                    tmp = tmp.next;
                }
                node.next = tmp;
            }
        }

        return head;
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
