package Difficult.设计跳表;

import java.util.Random;

public class Skiplist {

    private static final Random R = new Random();  // a randomness element

    private static final double P = 0.25;  // a probability of generating a new level

    private static final int MAXLEVEL = 32;  // max levels

    Node head = new Node(null,MAXLEVEL); // head node

    int currentLevel = 1; // the fact level about current node from one.

    // structure method
    public Skiplist() {}

    // random a level and at least one.
    private int randomLevel() {
        int level = 1;
        // flip and not crossed.
        while (R.nextDouble() < P && level < MAXLEVEL) {
            level++;
        }
        return level;
    }

    // find level floor that value is bigger than node's value.
    private Node findClosest(Node node, int levelIndex, int value) {
        // it will break when this node value is equal or bigger in current level.
        while ((node.next[levelIndex]) != null && value > node.next[levelIndex].value) {
            node = node.next[levelIndex];
        }
        return node;
    }

    // return target is or not is in the skiplist.
    public boolean search(int target) {
        Node searchNode = head;
        for (int i = currentLevel-1; i >=0; i--) {  // top level
            searchNode = findClosest(searchNode, i, target);  // in the level, finding the closest node.
            if (searchNode.next[i]!=null && searchNode.next[i].value == target){
                return true;
            }
        }
        return false;
    }

    // add a new element into skiplist
    public void add(int num) {
        int level = randomLevel();
        Node updateNode = head;
        Node newNode = new Node(num,level);
        // 计算出当前num 索引的实际层数，从该层开始添加索引
        for (int i = currentLevel-1; i>=0; i--) {
            //find a big smaller than this node and link it.
            updateNode = findClosest(updateNode,i,num);
            if (i<level){  // if next is null.
                if (updateNode.next[i]==null){
                    updateNode.next[i] = newNode;
                }else{ // not null
                    Node temp = updateNode.next[i];
                    updateNode.next[i] = newNode;
                    newNode.next[i] = temp;
                }
            }
        }

        // generate new node during current level and level.
        // and head of these level is newNode.
        if (level > currentLevel){
            for (int i = currentLevel; i < level; i++) {
                head.next[i] = newNode;
            }
            currentLevel = level;  // update current max level.
        }
    }

    // delete a special element if it is exists, you could delete either one.
    // or return false if it is not exists.
    public boolean erase(int num) {
        boolean flag = false;
        Node searchNode = head;
        for (int i = currentLevel-1; i >=0; i--) {
            searchNode = findClosest(searchNode, i, num);
            if (searchNode.next[i]!=null && searchNode.next[i].value == num){
                // skip this node and implement delete operation.
                searchNode.next[i] = searchNode.next[i].next[i];
                flag = true;
            }
        }
        return flag;
    }
}

class Node {
    /*
        the size of the node is max level.
        and we should update every level node by a element "current level".
     */
    Integer value; // node value
    Node[] next;   // point to next node in every level.

    // the element of size marks this node's level that one to size.
    public Node(Integer value, int size) {
        this.value = value;
        this.next = new Node[size];
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
