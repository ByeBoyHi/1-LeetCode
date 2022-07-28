package Easy.删除链表中的节点;

public class Solution {

    // 与下一个节点交换值
    public void deleteNode(ListNode node) {
        ListNode cur = node;
        while (cur.next.next!=null){
            int temp = cur.val;
            cur.val = cur.next.val;
            cur.next.val = temp;
            cur = cur.next;
        }
        int temp = cur.val;
        cur.val = cur.next.val;
        cur.next.val = temp;
        cur.next = null;
    }

    // 官解
    public void deleteNode2(ListNode node){
        node.val = node.next.val;  // 用后面的值覆盖前面的值，相当于前移
        node.next = node.next.next;  // 当前节点的next跳过本来的next，去了next的下一个。
    }
}

class ListNode{
    int val;
    ListNode next;
    ListNode(int x) { this.val = x;}
}
