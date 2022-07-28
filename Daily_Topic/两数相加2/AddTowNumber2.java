package Daily_Topic.两数相加2;

import java.util.Stack;

/**
 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。
 它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 输出：7 -> 8 -> 0 -> 7
*/
public class AddTowNumber2 {
    public static class ListNode{
        int val;
        ListNode next;
        public ListNode(int val) {
            this.val = val;
        }
    }
    public static ListNode addTwoNumbers(ListNode l1,ListNode l2){
        // 定义两个栈用来存这两个链表   --  先进后出，可以从后往前加
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        // 依次进行存储
        while (l1 != null){
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null){
            stack2.push(l2.val);
            l2 = l2.next;
        }
        // carry 用来进位
        int carry = 0;
        ListNode res = null; // 这里定义null可以防止一会儿拼接的时候，出现多一位数。
        // stack1没有全部空，或者stack2没有全部空，或者carry里面的进位没有加进去，都不退出
        // 三个条件都不满足后，就退出循环
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry != 0){
            // 是否已经空了，如果空了，则是0，否则就弹出栈顶元素
            int m = stack1.isEmpty() ? 0 :stack1.pop();
            int n = stack2.isEmpty() ? 0 :stack2.pop();
            // 加起来
            int sum = m + n + carry;
            // 判断是否进位
            carry = sum>=10 ? 1 : 0;
            // 取余
            sum = sum%10;

            // 定义一个新的Node，把sum存进去
            ListNode temp = new ListNode(sum);
            temp.next = res;
            // temp是临时的，所以把要返回的链表再重新定义到表头
            // 这里虽然res往前走了以为，但是原来的那个位置的值还在，可以通过引用类型来进行证明。
            res = temp;
        }
        return res;
    }

    public static void main(String[] args) {
        //1123
        ListNode l1 = new ListNode(1);
        ListNode l11 = new ListNode(1);
        ListNode l111 = new ListNode(2);
        ListNode l1111 = new ListNode(3);
        l1.next = l11;
        l11.next = l111;
        l111.next = l1111;
        //132
        ListNode l2 = new ListNode(1);
        ListNode l22 = new ListNode(2);
        ListNode l222 = new ListNode(3);
        l2.next = l22;
        l22.next = l222;
        //1123+123=1246
        ListNode res = addTwoNumbers(l1,l2);
        while (res != null){
            System.out.print(res.val);
            res = res.next;
        }
    }
}
