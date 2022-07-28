package Easy.回文链表;


public class ReverseLink {
    // 普通解法
    /*
        数组暴力：4ms
        链表暴力：9ms
     */
    public boolean isPalindrome(ListNode head) {
        int len = 0;
        ListNode h = head;
        while (head!=null){
            head = head.next;
            len++;
        }
        int left = 0;
        int right = len-1;
        int[] arr = new int[len];
        while (h!=null){
            arr[len-1] = h.val;
            len--;
            h = h.next;
        }
        while (left <= right) {
            if (arr[left]!=arr[right]) return false;
            left++;
            right--;
        }

        return true;
    }

    // O1 On
    /*
        我让后半截的链表反着指回来
        4ms
     */
    public boolean isPalindrome2(ListNode head) {
        if (head.next==null) return true;
        int len = getLen(head);
        int half = len/2;
        ListNode left = head; // 左边的头部
        if ((len&1)==0) half--; // 偶数个的话，我们就走到中间
        while (half>0){
            head = head.next;
            half--;
        }
        ListNode pre = head;
        ListNode cur = head.next;
        while (cur!=null){
            ListNode t = pre;
            pre = cur.next;
            cur.next = t;
            t = cur;
            cur = pre;
            pre = t;
        }
        // 最后pre就是右边的头部
        while (true){
            if (left.val!=pre.val) return false;
            if (left.next == pre || left==pre) break;
            left = left.next;
            pre = pre.next;
        }

        // 没有恢复现场

        return true;
    }

    public int getLen(ListNode head){
        ListNode h = head;
        int len = 0;
        while (h!=null){
            h = h.next;
            len++;
        }
        return len;
    }

    // 官解O1
    // 6ms
    public boolean isPalindrome3(ListNode head) {
        if (head == null) {
            return true;
        }

        // 找到前半部分链表的尾节点并反转后半部分链表
        ListNode firstHalfEnd = endOfFirstHalf(head);
        ListNode secondHalfStart = reverseList(firstHalfEnd.next);

        // 判断是否回文
        ListNode p1 = head;
        ListNode p2 = secondHalfStart;
        boolean result = true;
        while (result && p2 != null) {
            if (p1.val != p2.val) {
                result = false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        // 还原链表并返回结果
        firstHalfEnd.next = reverseList(secondHalfStart);
        return result;
    }

    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    private ListNode endOfFirstHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
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
