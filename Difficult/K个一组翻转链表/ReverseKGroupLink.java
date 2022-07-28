package Difficult.K个一组翻转链表;

public class ReverseKGroupLink {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        print(head);
        head = reverse(head);
        System.out.println();
        print(head);
    }

    public static void print(ListNode head){
        while (head!=null){
            System.out.print(head.val+" ");
            head = head.next;
        }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (k==0 || head==null) return head;
        int len = 0;
        ListNode tmp = head;
        while (head!=null){
            len++;
            head = head.next;
        }
        int[] nums = new int[len];
        int idx = 0;
        while (tmp!=null){
            nums[idx++] = tmp.val;
            tmp = tmp.next;
        }
        for (int i=0; i+k<=len; i+=k){
            int left = i;
            int right = i+k-1;
            while (left<right){
                int t = nums[left];
                nums[left] = nums[right];
                nums[right] = t;
                left++;
                right--;
            }
        }
        ListNode ans = new ListNode(nums[0]);
        ListNode r = ans;
        for (int i=1; i<nums.length; i++){
            ans.next = new ListNode(nums[i]);
            ans = ans.next;
        }

        return r;
    }

    // 反转链表：前插法
    public static ListNode reverse(ListNode head){
        ListNode ans = head;
        head = head.next;
        ans.next = null;
        while (head!=null){
            ListNode t = head.next;
            head.next = ans;
            ans = head;
            head = t;
        }
        return ans;
    }

    // 每k个一翻转，不足则直接退出
    public ListNode reverseKGroup2(ListNode head, int k) {
        ListNode hair = new ListNode();
        hair.next = head;
        ListNode pre = hair;
        while (head!=null){
            ListNode right = head;
            for (int i=1; i<k; i++){//移动k-1步
                right = right.next;
                if (right==null) return hair.next;
            }
            ListNode next = right.next;
            ListNode[] cur = reverse(head, right);
            pre.next = cur[0];
            cur[1].next = next;
            pre = cur[1];
            head = pre.next;
        }

        return hair.next;
    }

    // 内部反转链表
    /*
    模拟过程：
             1 -> 2 -> 3 -> end
        ==》 2 -> 3 -> end <- 1
        ==》 3 -> end <- 1 <- 2
        ==》 3 -> 2 -> 1 -> end
     */
    public ListNode[] reverse(ListNode head, ListNode tail) {
        ListNode prev = tail.next;
        ListNode p = head;
        while (prev != tail) {
            ListNode nex = p.next;
            p.next = prev;
            prev = p;
            p = nex;
        }
        return new ListNode[]{tail, head};
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
