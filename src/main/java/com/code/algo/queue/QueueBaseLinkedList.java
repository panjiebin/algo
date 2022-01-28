package com.code.algo.queue;

/**
 * 基于链表实现队列
 * <p> 用指针head,tail分别表示队头和队尾结点，入队：tail.next = new_node,tail=tail.next；
 * 出队：head = head.next
 * <p>出队和入队的时间复杂度：O(1)
 * @author panjb
 */
public class QueueBaseLinkedList<E> implements Queue<E> {

    private Node<E> head;
    private Node<E> tail;

    public static void main(String[] args) {
        QueueBaseLinkedList<String> queue = new QueueBaseLinkedList<>();
        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        System.out.println(queue.dequeue());
        queue.enqueue("d");
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
    }

    @Override
    public boolean enqueue(E element) {
        Node<E> node = new Node<>(element);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = tail.next;
        }
        return true;
    }

    @Override
    public E dequeue() {
        if (head == null) {
            return null;
        }
        Node<E> node = head;
        head = head.next;
        return node.val;
    }

    private static class Node<E> {
        private E val;
        private Node<E> next;

        public Node() {
        }

        public Node(E val) {
            this.val = val;
        }
    }
}
