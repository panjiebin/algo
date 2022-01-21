package com.code.algo.stack;

/**
 * 链式栈：基于链表实现的栈
 * <p>为了满足后进先出的特点，入栈：插入到头结点下一个结点；出栈：返回头结点的下一个结点。
 * 即越靠近链表尾部，越早进入栈，栈顶元素为head.next
 * <p>出栈、入栈时间复杂度：O(1)
 * @author panjb
 */
public class StackBaseLinkedList<E> {
    private final Node<E> head = new Node<>();

    /**
     * 入栈
     *
     * @param element 元素
     */
    public void push(E element) {
        Node<E> node = new Node<>(element);
        node.next = head.next;
        head.next = node;
    }

    public E pop() {
        if (head.next == null) {
            return null;
        }
        Node<E> peek = head.next;
        head.next = head.next.next;
        return peek.element;
    }

    private static class Node<E> {
        E element;
        private Node<E> next;

        public Node() {
        }

        public Node(E element) {
            this.element = element;
        }
    }
}
