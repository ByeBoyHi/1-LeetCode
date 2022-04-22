package Easy.两个非等长链表合并并保证有序;

class mergeTwoListsTest {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l11 = new ListNode(2);
        ListNode l111 = new ListNode(4);
        ListNode l2 = new ListNode(1);
        ListNode l22 = new ListNode(3);
        ListNode l222 = new ListNode(4);
        ListNode l2222 = new ListNode(5);
        ListNode l22222 = new ListNode(8);
        ListNode l222222 = new ListNode(3);
        l1.next = l11;
        l11.next = l111;
        l2.next = l22;
        l22.next = l222;
        l222.next = l2222;
        l2222.next = l22222;
        l22222.next = l222222;
        ListNode n = mergeTwoLists(l1,l2);
        while (n != null){
            System.out.print(n);
            n = n.next;
        }
    }
    public static ListNode mergeTwoLists(ListNode l1,ListNode l2){
        if (l1 == null && l2 == null){
            return null;
        }else if (l1 == null){
            return l2;
        }else if (l2 == null){
            return l1;
        }
        // l1 1 2     4
        // l2 1 3     4
        /**
         * 1. l1=1 l2=1  -->  l2.next = mergeTwoLists(l1,l2.next)
         * 2. l1=1 l2=3  -->  l1.next = mergeTwoLists(l1.next,l2)
         * 3. l1=2 l2=3  -->  l1.next = mergeTwoLists(l1.next,l2)
         * 4. l1=null l2=3  -->  return l2(3);
         * 5. l1.next = l2  --> return l1(2);
         * 6. l1.next = l1(2)  --> return l1(1);
         * 7. l2.next = l1(1)  --> return l2(1);
         * 8.   Over
         */
        // 这个递归就相当于是两个链表不断地比较下去，谁大就走哪一条支路
        // 最后比到一个空了后，剩下的就是最大的那个值，再依次返回回来。
        if (l1.val < l2.val){
            l1.next =  mergeTwoLists(l1.next,l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1,l2.next);
            return l2;
        }
    }
}

class ListNode{
    int val;
    ListNode next;
    public ListNode(int val){
        this.val = val;
    }
    @Override
    public String toString() {
        return this.next == null?val+"":val+"->";
    }
}
