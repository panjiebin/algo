package com.code.algo.linked;

/**
 * 单链表操作
 * <ul>
 *     <li>单链表反转</li>
 *     <li>链表中环的检测</li>
 *     <li>两个有序链表的合并</li>
 *     <li>删除链表倒数第n个节点</li>
 *     <li>求链表的中间节点</li>
 * </ul>
 *
 * @author panjb
 */
public class SinglyLinkedListOp {

    public static void main(String[] args) {
        ListNode head = ListNode.newListNode(new int[]{1, 2, 3, 4, 5});
        ListNode inverse = inverse(head);
        inverse.print();
    }

    /**
     * 单链表反转(LeetCode 206)
     * <p>从尾结点开始添加前驱结点</p>
     * <p>时间复杂度：O(n)，空间复杂度：O(1)</p>
     *
     * @param head 头结点
     * @return 反转后的链表
     */
    public static ListNode inverse(ListNode head) {
        if (head == null) {
            return null;
        }
        //从尾结点开始
        ListNode next = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode tmp = curr.next;
            curr.next = next;
            next = curr;
            curr = tmp;
        }
        return next;
    }

    public static boolean hasCycle(ListNode head) {

        return false;
    }
}
