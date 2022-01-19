package com.code.algo.linked;

/**
 * 单链表结点
 * <p> 由存储数据和后继指针构成
 *
 * @author panjb
 */
public class ListNode {
    /**存储数据*/
    public int val;
    /**后继指针：指向下一个节点*/
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public void print() {
        ListNode curr = this;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
    }

    public static ListNode newListNode(int[] arr) {
        ListNode head = new ListNode();
        ListNode curr = head;
        for (int i : arr) {
            curr.next = new ListNode(i);
            curr = curr.next;
        }
        return head.next;
    }
}
