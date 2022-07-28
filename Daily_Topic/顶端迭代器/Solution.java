package Daily_Topic.顶端迭代器;

import java.util.Iterator;

// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html

class PeekingIterator1 implements Iterator<Integer> {

    private Iterator<Integer> iterator;  // 传进来的数组
    private Integer cache = null;  // 第一次Peek的时候，缓存迭代的元素

    public PeekingIterator1(Iterator<Integer> iterator) {
        // initialize any member here.
        this.iterator = iterator;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        if (cache == null){ // 如果cache等于空，说明这是第一次peek
            cache = iterator.next();
        }
        return cache;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        if (cache==null){  // 还没有peek过
            return iterator.next();
        }
        // peek过
        // 根据peek的运行原理，cache存储的必然是next元素
        // 存下来返回出去，然后让cache置空，便于下次peek
        Integer temp = cache;
        cache = null;
        return temp;
    }

    @Override
    public boolean hasNext() {
        // cache不为空，说明里面至少有一个元素
        // 否则就用自己的迭代对象找一下是否有下一个元素

        return cache!=null || iterator.hasNext();
    }
}


// 官解：加一个泛型，可以让扩展到任意类型
class PeekingIterator2 implements Iterator<Integer> {
    private Iterator<Integer> iterator;
    private Integer nextElement;

    public PeekingIterator2(Iterator<Integer> iterator) {
        this.iterator = iterator;
        nextElement = iterator.next();  // 把当前元素给nextElement，然后指针后移
    }

    public Integer peek() {
        return nextElement;
    }

    @Override
    public Integer next() {
        Integer ret = nextElement;
        nextElement = iterator.hasNext() ? iterator.next() : null;
        return ret;
    }

    @Override
    public boolean hasNext() {
        return nextElement != null;
    }
}