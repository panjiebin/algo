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

    /**
     * 链表中环的检测(LeetCode 141)
     * <p>
     * 利用快慢指针，慢指针：每次跳跃一个结点，快指针每次跳跃两个结点，当快慢指针相遇时，表示有环
     * <p>时间复杂度：O(n)，空间复杂度：O(1)</p>
     *
     * @param head 头结点
     * @return 有环-true，反之-false
     */
    public static boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        //快慢指针
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            //快慢指针相遇
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    /**
     * 合并两个有序链表(LeetCode 21)
     *
     * <p>用两个指针 l1,l2 分别指向两个有序链表，用一个指针 curr 指向合并结果链表
     * <p>比较l1,l2对应数据结点的值：
     * 当l1.val < l2.val时，curr.next = l1, l1前进一步，反之curr.next = l1，l2前进一步；每一次比较，curr前进一步。
     * 直到l1和l2其中有一个为空时，停止迭代比较。将不为空的链表直接接到合并链表后面，即curr.next = l1 / l2
     * <p>时间复杂度：O(m + n)，空间复杂度：O(1)</p>
     * @param l1 有序链表1
     * @param l2 有序链表2
     * @return 合并链表
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //哨兵结点，方便获取合并结果链表
        ListNode dummy = new ListNode();
        ListNode curr = dummy;
        //迭代比较l1和l2
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        //不为空的链表直接接到合并链表之后即可
        if (l1 != null) {
            curr.next = l1;
        }
        if (l2 != null) {
            curr.next = l2;
        }
        return dummy.next;
    }

    /**
     * 删除链表倒数第n个节点(LeetCode 19)
     * <p><b>思路：</b>利用两个指针，first比second超前n个结点，当first走到链表尾部时，second刚好走到要删除的结点
     * <p> 为什么first到链表末尾，second就是要删除的结点？
     * <p> 假设链表长度为m，要删除倒数第n个结点，从head出发则是第k=m-n+1个结点，因此当first先从遍历n个结点，first走到末端（NULL）刚好等于k=m-n+1
     * <p>first,second一开始都指向head，first先向前移动n个结点，使first比second超前n个结点，即first与second之间间隔n-1结点.
     * 然后同时让first,second一起遍历链表，当first遍历到链表尾部，即first=NULL，second指向的节点就是要删除的结点。由于单链表需要再找到
     * second的前驱节点才能删除。
     * 为了方便我们可以利用哨兵简化，创建一个哑结点，指向head，second指向哑结点，则当first走到链表尾部，second走到要删除节点的前驱节点。
     *
     * <p>时间复杂度：O(n)，空间复杂度：O(1)
     * <p>双指针思路可能不太好想得到，利用栈的特性的思路比较容易想到。
     * 栈：先从head开始将链表所有结点入栈，然后再将n个结点出栈，第n个出栈的结点就是要删除的结点
     * <p>栈的时间复杂度：O(n)，空间复杂度：O(n)
     * @param head 头结点
     * @param n 倒数第n个节点
     * @return 删除倒数第n个节点的链表
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        //哑结点
        ListNode dummy = new ListNode(-1, head);
        ListNode first = head;
        ListNode second = dummy;
        //first指针先开始遍历n个节点
        for (int i = 0; i < n; i++) {
            first = first.next;
        }
        //first,second同时遍历链表，当first走到末尾，second恰好是要删除结点的前驱结点
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }

    /**
     * 求链表的中间节点(LeetCode 876)
     * <p>利用快慢指针，慢指针：每次跳跃一个结点，快指针：每次跳跃两个结点，当快指针走到底时，慢指针所在结点就是中间结点
     * <p>时间复杂度：O(n)，空间复杂度：O(1)
     * @param head 头结点
     * @return 链表中间结点
     */
    public static ListNode middleNode(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
