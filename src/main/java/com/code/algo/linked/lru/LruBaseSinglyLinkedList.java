package com.code.algo.linked.lru;

/**
 * 基于单链表实现的LRU缓存
 * <p>
 * LRU：用一个定长的有序链表存储缓存数据，越靠近链表尾部的数据表示越旧，链表头部节点最新
 * <p>缓存操作
 * <ul>
 *     <li>访问：当访问链表中一个数据时，需要将数据对应结点从原来的位置删除，并插入到链表的头部</li>
 *     <li>插入：当缓存未满时，直接插入链表头部；当缓存满时，删除尾结点，再将新的数据结点插入链表头部</li>
 * </ul>
 * <p>访问缓存的时间复杂度是O(n)，可以通过散列表降低到O(1)</p>
 *
 * @author panjb
 */
public class LruBaseSinglyLinkedList<T> {
    /** 容量 */
    private final int capacity;
    /** 头结点，是一个哨兵结点，方便操作*/
    private final Node<T> head = new Node<>();
    /** 当前结点数量 */
    private int count = 0;

    public LruBaseSinglyLinkedList(int capacity) {
        this.capacity = capacity;
        this.count = 0;
    }

    public void put(T data) {
        //查找前驱结点，如果先查找数据结点，后面删除前还得遍历得到它的前驱结点，多了一趟遍历
        Node<T> prevNode = findPrevNode(data);
        //缓存中没有，直接插入
        if (prevNode == null) {
            this.doInsert(new Node<>(data));
        } else {
            //缓存中有，先删除原来位置数据，再插入头部
            moveNextNodeToHead(prevNode);
        }
        //超过容量，删除尾结点
        if (count > capacity) {
            this.deleteTail();
        }
    }

    private void moveNextNodeToHead(Node<T> prevNode) {
        Node<T> node = prevNode.next;
        this.doDeleteNextNode(prevNode);
        this.doInsert(node);
    }

    public T get(T data) {
        Node<T> prevNode = this.findPrevNode(data);
        if (prevNode == null) {
            return null;
        }
        //删除原来位置的值，再移到头部
        moveNextNodeToHead(prevNode);
        return head.next.val;
    }

    public boolean delete(T data) {
        Node<T> prevNode = this.findPrevNode(data);
        if (prevNode == null) {
            return false;
        }
        this.doDeleteNextNode(prevNode);
        return true;
    }

    public void printAll() {
        Node<T> curr = head.next;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    private void doInsert(Node<T> node) {
        node.next = head.next;
        head.next = node;
        this.count++;
    }

    private void doDeleteNextNode(Node<T> prevNode) {
        prevNode.next = prevNode.next.next;
        this.count--;
    }

    private Node<T> findPrevNode(T data) {
        Node<T> prev = head;
        while (prev != null) {
            if (prev.next != null && prev.next.val.equals(data)) {
                return prev;
            }
            prev = prev.next;
        }
        return null;
    }

    private void deleteTail() {
        Node<T> prev = head;
        //空链表，直接返回
        if (prev.next == null) {
            return;
        }
        //迭代获取尾结点的前驱结点
        while (prev.next.next != null) {
            prev = prev.next;
        }
        prev.next = null;
        this.count--;
    }

    private static class Node<T> {
        T val;
        Node<T> next;

        public Node() {
        }

        public Node(T val) {
            this.val = val;
        }
    }

}
