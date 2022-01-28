package com.code.algo.queue;

/**
 * 基于数组实现的循环队列
 * <p>实现循环队列的关键：<code>队空和队满的判断条件</code>
 * <ul>
 *     <li>队空：head == tail </li>
 *     <li>队满：(tail+1) % capacity == head </li>
 * </ul>
 * <p>出队和入队时间复杂度：O(1)
 * @author panjb
 */
public class CircularQueue<E> implements Queue<E> {
    private final Object[] elements;
    private final int capacity;
    private int head;
    private int tail;

    public CircularQueue(int capacity) {
        this.elements = new Object[capacity];
        this.capacity = capacity;
    }

    @Override
    public boolean enqueue(E element) {
        //队满
        if ((tail + 1) % capacity == head) {
            return false;
        }
        elements[tail] = element;
        tail = (tail + 1) % capacity;
        return true;
    }

    @Override
    public E dequeue() {
        //队空
        if (tail == head) {
            return null;
        }
        Object element = this.elements[head];
        head = (head + 1) % capacity;
        return (E) element;
    }
}
