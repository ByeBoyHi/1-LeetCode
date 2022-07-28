package Middle.两数相加;

/**
 * 最小内存代码
 *
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution3 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //先判空
        if(l1 == null){
            return l2;
        }else if(l2 == null){
            return l1;
        }
//反转l1
        ListNode cur = l1;
        ListNode prev = null;
        while(cur != null){
            ListNode curNext = cur.next;
            cur.next = prev;
            prev = cur;
            cur = curNext;
        }
//反转l2
        ListNode cur1 = l2;
        ListNode prev1 = null;
        while(cur1 != null){
            ListNode curNext1 = cur1.next;
            cur1.next = prev1;
            prev1 = cur1;
            cur1 = curNext1;
        }
//定义tmp作为相加的和
        int tmp = 0;
//定义两个亚节点,一个用来便利,一个用来记录最终结果
        ListNode result = new ListNode(0);
        ListNode resultt = result;
//两个数相加,和加法一样,不过是向左进位,这个和两数相加那题一样,可以先做那一题
        while(prev != null || prev1 != null){
            tmp = tmp + (prev != null ? prev.val : 0)+(prev1 != null ? prev1.val : 0);
            result.next = new ListNode(tmp % 10);
            prev = (prev != null ? prev.next : null);
            prev1 = (prev1 != null ? prev1.next : null);
            result = result.next;
            tmp = tmp / 10;
        }
//最后还得再把tmp最后进的位再附上
        if(tmp != 0){
            result.next =new ListNode(tmp);
        }
//再把最后的链表做一次反转即可
        ListNode prev2 = null;
        ListNode head = resultt.next;
        ListNode cur2 = head;
        while(cur2 != null){
            ListNode curNext2 = cur2.next ;
            cur2.next = prev2;
            prev2 = cur2;
            cur2 = curNext2;
        }
        return prev2;


    }}
