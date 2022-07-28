package Daily_Topic.对链表进行插入排序;

/**
 * @author 唐
 * @date 2020/11/20 0020
 * @time 11:33
 */

/**
 * 本题新学单词：
 * 1. redundant: 多余的
 * 2. dundant: 泛滥的
 * 3. insertion: 插入
 * 4. evaluate: 评估
 * 5. flow: 流、流动 / 流量、泛滥
 */

public class Solution {

    public static void main(String[] args) {
        ListNode p1 = new ListNode(-1);
        ListNode p2 = new ListNode(5);
        ListNode p3 = new ListNode(3);
        ListNode p4 = new ListNode(4);
        ListNode p5 = new ListNode(0);

        p1.next = p2;
        p2.next = p3;
        p3.next = p4;
        p4.next = p5;
        p5.next = null;

        System.out.println(new Solution().insertionSortList2(p1));
    }

    private ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode temp;
        //每次判断下一个节点head.next是否为空，不为空才进行插入操作
        while (head.next!=null){
            // 若head.val较小，则此节点已在正确的位置，无需后续查找插入
            if (head.val<=head.next.val){
                head = head.next;
                continue;
            }

            temp=head.next;  // 上面的if没有进去，说明这个next是较小值，暂时存放在temp中
            head.next = temp.next;  // 相当于：head.next = head.next.next 跳过了中间那个较小值
            // temp.next=null;  // 清理掉较小值后面的东西，便于作为单独的节点进行插入位置的定位


            // 对整个链表前n个节点进行定位查找
            insertion (dummy,temp);

        }
        return dummy.next;
    }

    // 查找插入点插入
    private void insertion(ListNode head,ListNode insert){
        // 当head.next.val > insert.val时，insert的位置就在head和head.next之间
        while (head.next.val<insert.val){
            head = head.next;
        }
        insert.next = head.next;
        head.next = insert;
    }

    public ListNode insertionSortList2(ListNode head) {
        if (head==null){
            return null;
        }
        ListNode res = new ListNode(0);  // 用来指向head，便于取值
        res.next = head;
        ListNode temp; // 用来存储要插入新的位置的元素
        while (head.next!=null){ // head一直往后走进行排序
            if (head.val<head.next.val){  // 如果当前值比它下一个值小，就不用进行后面的
                head = head.next;
                continue;
            }

            // 存较小值
            temp = head.next;
            // 让较大值指向较小值后面的那个，跳过较小值
            head.next = temp.next;

            // 新建一个节点作为res，往后找插入位置
            ListNode node = res;
            // 只要下一个节点非空，并且下一个节点的值小于较小值
            while (node.next!=null && node.next.val<temp.val){
                // 就让节点后移
                node = node.next;
            }

            // 让较小值的指针指向新建节点的下一个节点
            temp.next = node.next;
            // 让新建节点的next指向较小值
            node.next = temp;
        }
        return res.next;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return this.val+(this.next!=null?"->"+this.next:"");
    }
}
