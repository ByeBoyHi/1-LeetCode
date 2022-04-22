package Easy.移除链表元素;

import java.util.ArrayList;
import java.util.List;

public class DeleteLinkFactory {
    public ListNode removeElements(ListNode head, int val) {
        if(head==null) return null;
        while(head!=null && head.val==val){
            head = head.next;
        }
        if(head==null) return null;  // 说明全是我们要删除的节点
        ListNode nxt = head.next;
        ListNode cur = head;
        while(nxt!=null){
            if(nxt.val==val){  // 找到第一个不是val的元素
                nxt = nxt.next;
            }else{  // 让当前元素指向不是val的元素，然后移动到这里
                cur.next = nxt;
                cur = nxt;
                nxt = cur.next;
            }
        }

        if (null !=cur.next){  // 说明退出循环的时候，nxt一路跳过了val元素
            cur.next=null;
        }

        return head;
    }

    public ListNode removeElements2(ListNode head, int val) {
        // 直接存在数组里面
        ListNode ans = new ListNode();
        List<Integer> arr = new ArrayList<>();
        while (head!=null){
            if (head.val!=val){
                arr.add(head.val);
            }
            head = head.next;
        }
        ListNode tp = ans;
        for (int i: arr){
            tp.next = new ListNode(i);
            tp = tp.next;
        }
        return ans.next;
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
