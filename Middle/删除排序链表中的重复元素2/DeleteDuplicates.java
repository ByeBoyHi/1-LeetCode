package Middle.删除排序链表中的重复元素2;

public class DeleteDuplicates {
    public ListNode deleteDuplicates(ListNode head) {
        if (head==null) return head;
        ListNode ans = new ListNode();
        ListNode res = ans;
        ListNode pre = head;
        ListNode cur = head.next;
        while (cur!=null){
            // 这个节点如果是紧邻的节点，并且这个节点的值和上一个节点的值不一样
            // 说明上一个节点就是不重复的节点
            // 把不重复的节点链接到ans链表上
            // pre要移动到新的节点用来进行下一次判断
            if (cur==pre.next && cur.val!=pre.val){
                ans.next = pre;
                ans = pre;
                pre = cur;
            }else if (cur.val!=pre.val){
                // 当出现新的不同的值，但是又不是邻近的点
                // 说明之前的pre是重复值，直接抛弃，让pre移动到新的位置
                pre = cur;
            }
            cur = cur.next;
        }
        // 整个节点判断完后，需要判断最后一个节点是否需要添加
        if (pre.next==null){
            ans.next = pre;
        }

        return res.next;
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