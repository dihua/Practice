package leetcode;

/**
 * @author dihua.wu
 * @description 两数相加
 * @create 2020/5/26
 */
class Solution2TwoAdd {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     * <p>
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     * <p>
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * <p>
     * 示例：
     * <p>
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/add-two-numbers
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param l1 链表1
     * @param l2 链表2
     * @return ListNode
     */

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //进位
        int carry = 0;
        //头节点 p q
        ListNode p = l1;
        ListNode q = l2;
        //新链表
        ListNode resultListNode = new ListNode(0);
        //新链表头节点
        ListNode currentListNode = resultListNode;

        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = x + y + carry;
            carry = sum / 10;
            currentListNode.next = new ListNode(sum % 10);
            currentListNode = currentListNode.next;
            if (p != null) {
                p = p.next;
            }
            if (q != null) {
                q = q.next;
            }
        }
        //最后一位存在进位，追加一个1节点
        if (carry > 0) {
            currentListNode.next = new ListNode(carry);
        }
        return resultListNode.next;
    }


    public static void main(String[] args) {
        ListNode listNode11 = new ListNode(2);
        ListNode listNode12 = new ListNode(4);
        ListNode listNode13 = new ListNode(8);
        listNode11.next = listNode12;
        listNode12.next = listNode13;

        ListNode listNode21 = new ListNode(5);
        ListNode listNode22 = new ListNode(6);
        ListNode listNode23 = new ListNode(4);
        listNode21.next = listNode22;
        listNode22.next = listNode23;

        ListNode node = addTwoNumbers(listNode11, listNode21);
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }


}