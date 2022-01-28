package com.code.algo.queue;

/**
 * 基于数组实现队列
 * <p>使用两个下标head,tail分别表示队头和队尾。
 * 入队，tail加1，当tail==capacity且head==0时，表示队列已满；当tail==capacity且head!=0时，需要将数组数据集体左移
 * 出队，直接返回head对应的元素，head+1，当head==tail时，表示队列已空
 * <p>出队入队时间复杂度：O(1)</p>
 * @author panjb
 */
public class QueueBaseArray<E> implements Queue<E> {

    private final Object[] elements;
    private final int capacity;
    private int head;
    private int tail;

    public QueueBaseArray(int capacity) {
        this.capacity = capacity;
        this.elements = new Object[capacity];
        head = 0;
        tail = 0;
    }

    public static void main(String[] args) {
        QueueBaseArray<String> queue = new QueueBaseArray<>(3);
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        System.out.println(queue.dequeue());
        boolean d = queue.enqueue("d");
        System.out.println("d = " + d);
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
    }

    @Override
    public boolean enqueue(E element) {
        if (tail == capacity) {
            //对头在初始位置，表示队列已满
            if (head == 0) {
                return false;
            }
            //将head~tail之间数据集体左移
            for (int i = head; i < tail; i++) {
                this.elements[i - head] = this.elements[i];
            }
            tail -= head;
            head = 0;
        }
        this.elements[tail] = element;
        tail++;
        return true;
    }

    @Override
    public E dequeue() {
        if (head == tail) {
            return null;
        }
        Object element = this.elements[head];
        head++;
        return (E) element;
    }
}
