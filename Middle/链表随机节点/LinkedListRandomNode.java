package Middle.链表随机节点;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LinkedListRandomNode {
    ListNode head;
    int size = 0;
    public LinkedListRandomNode(ListNode head) {
        this.head = head;
        while (head!=null){
            size++;
            head = head.next;
        }
    }

    public int getRandom() {
        int rand = (int)(Math.random()*this.size);
        ListNode cur = head;
        while (rand>0){
            cur = cur.next;
            rand--;
        }
        return cur.val;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class Solution {
    List<Integer> list;
    Random random;

    public Solution(ListNode head) {
        list = new ArrayList<Integer>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        random = new Random();
    }

    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }
}

class Solution2 {
    ListNode head;
    Random random;

    public Solution2(ListNode head) {
        this.head = head;
        random = new Random();
    }

    public int getRandom() {
        int i = 1, ans = 0;
        /*
            水塘抽样：
            这里的等概率是每个被选中的概率，从[0,bound)之间选一个整数
            P(i==0)表示第i个值被选中的概率，这里被选中且后面的都不被选中的概率，都是等于 1/n 的。
            P(i==0)*P(i+1!=0)*P(i+2!=0)*...*P(n!=0)
            = 1/i * (1-1/(i+1)) * ... * (1-1/n)
            = 1/i * i/(i+1) * ... * (n-1)/n
            = 1/n
         */
        for (ListNode node = head; node != null; node = node.next) {
            if (random.nextInt(i) == 0) { // 1/i 的概率选中（替换为答案）
                ans = node.val;
            }
            ++i;
        }
        return ans;
    }
}
