package Middle.设计循环队列;

public class MyCircularQueue {

    int size;
    int k;
    Node head, tail;

    // init capacity equal to k.
    public MyCircularQueue(int k) {
        size = 0;
        this.k = k;
        // 重新定义了两个节点来构建这个新的链表
        // 处理了之前一个节点的问题：front一直返回 0 的问题？？
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.next = head;
    }

    // insert a new value
    public boolean enQueue(int value) {
        if (isFull()) return false;
        Node cur = new Node(value);
        tail.next = cur;
        tail = cur;
        cur.next = head;
        size++;
        if (size==1){
            head.next = cur;
        }
        return true;
    }

    // remove a value
    public boolean deQueue() {
        if (isEmpty()) return false;
        head.next = head.next.next;
        size--;
        return true;
    }

    // head value
    public int Front() {
        if (isEmpty()) return -1;
        return head.next.val;
    }

    // tail value
    public int Rear() {
        if (isEmpty()) return -1;
        return tail.val;
    }

    // is or not is empty
    public boolean isEmpty() {
        return size==0;
    }

    // is ro not is full
    public boolean isFull() {
        return size==k;
    }
}

class Node{
    int val;
    Node next;

    public Node() {}

    public Node(int val) {
        this.val = val;
        this.next = null;
    }

    public Node(Node next, int val) {
        this.next = next;
        this.val = val;
    }
}

class MyCircularQueue2 {

    int size = 0;
    int left;
    int right;
    int[] arr;
    int k;

    // init capacity equal to k.
    public MyCircularQueue2(int k) {
        left = right = 0;
        arr = new int[k];
        this.k = k;
    }

    // insert a new value
    public boolean enQueue(int value) {
        if (isFull()) return false;
        arr[size++] = value;
        return true;
    }

    // remove a value
    public boolean deQueue() {
        if (isEmpty()) return false;
        size--;
        if (size >= 0) System.arraycopy(arr, 1, arr, 0, size);
        return true;
    }

    // head value
    public int Front() {
        if (isEmpty()) return -1;
        return arr[0];
    }

    // tail value
    public int Rear() {
        if (isEmpty()) return -1;
        return arr[size-1];
    }

    // is or not is empty
    public boolean isEmpty() {
        return size==0;
    }

    // is ro not is full
    public boolean isFull() {
        return size==k;
    }
}
