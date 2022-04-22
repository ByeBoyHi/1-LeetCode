package Easy.将两个等长链表合并保证有序;

/**
 * 将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 *
 * 示例：
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 *
 */

public class mergeTwoListsTest1 {
    public static void main(String[] args) {
        listNode1 l1 = new listNode1(1);
        listNode1 l11 = new listNode1(2);
        listNode1 l111 = new listNode1(4);
        listNode1 l2 = new listNode1(1);
        listNode1 l22 = new listNode1(3);
        listNode1 l222 = new listNode1(4);
        l1.next = l11;
        l11.next = l111;
        l2.next = l22;
        l22.next = l222;
        listNode1 n = mergeTwoLists(l1, l2);
        while (n != null){
            System.out.print(n);
            n = n.next;
        }
    }
    public static listNode1 mergeTwoLists(listNode1 l1, listNode1 l2) {
        int p = 0;
        listNode1 listNode1 = l1;
        Easy.将两个等长链表合并保证有序.listNode1 listNode2 = l2;
        Easy.将两个等长链表合并保证有序.listNode1 res = new listNode1(0);
        //---------------------------------
        while (l1!=null){

            p++;
            l1 = l1.next;
        }
        while (l2!=null){
            p++;
            l2 = l2.next;
        }
        //---------------------------------
        int[] num = new int[p];
        int k = 0;
        while (listNode1!=null){
            num[k] = listNode1.val;
            k++;
            listNode1 = listNode1.next;
        }
        while (listNode2!=null){
            num[k] = listNode2.val;
            k++;
            listNode2 = listNode2.next;
        }
        //---------------------------------
        // 1 2 4 1 3 4
        //  冒泡排序
        for(int i = 0; i<num.length;i++) {
            for (int j = 0; j < num.length - i - 1; j++) {
                if (num[j] > num[j + 1]) {
                    int temp = num[j];
                    num[j] = num[j + 1];
                    num[j + 1] = temp;
                }
            }
        }
        //---------------------------------
        res.val = num[0];
        Easy.将两个等长链表合并保证有序.listNode1 node = new listNode1(0);
        res.next = node;
        for (int i=1; i<num.length ;i++){
            Easy.将两个等长链表合并保证有序.listNode1 node1 = new listNode1(0);
            node.val = num[i];
            if (i != num.length-1) {
                node.next = node1;
                node = node1;
            }
        }

        return res;
    }
}
class listNode1 {
    int val;
    listNode1 next;

    public listNode1(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return this.next == null?val+"":val+"->";
    }
}