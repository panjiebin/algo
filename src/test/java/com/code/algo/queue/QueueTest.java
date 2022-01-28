package com.code.algo.queue;

import org.junit.Test;

import static org.junit.Assert.*;

public class QueueTest {

    @Test
    public void testArrayQueue() {
        testQueue(new QueueBaseArray<>(3));
    }

    @Test
    public void testLinkedListQueue() {
        testQueue(new QueueBaseLinkedList<>());
    }

    @Test
    public void testCircularQueue() {
        testQueue(new CircularQueue<>(4));
    }


    private void testQueue(Queue<String> queue) {
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
}