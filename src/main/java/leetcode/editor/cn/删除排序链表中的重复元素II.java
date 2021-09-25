package leetcode.editor.cn;

//存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除链表中所有存在数字重复情况的节点，只保留原始链表中 没有重复出现 的数字。 
//
// 返回同样按升序排列的结果链表。 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [1,2,3,3,4,4,5]
//输出：[1,2,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [1,1,1,2,3]
//输出：[2,3]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点数目在范围 [0, 300] 内 
// -100 <= Node.val <= 100 
// 题目数据保证链表已经按升序排列 
// 
// Related Topics 链表 双指针 👍 709 👎 0

public class 删除排序链表中的重复元素II {
    public static void main(String[] args) {
        Solution solution = new 删除排序链表中的重复元素II().new Solution();
        int[] nums = new int[]{1, 2, 3, 3, 4, 4, 5};
        ListNode head = ListNode.arrayToNode(nums);
        solution.deleteDuplicates(head);
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
        public ListNode deleteDuplicates(ListNode head) {
            if (head == null) {
                return null;
            }
            ListNode tmpNode = new ListNode(0);
            tmpNode.next = head;
            ListNode curr = tmpNode;
            while (curr.next != null && curr.next.next != null) {
                if (curr.next.val == curr.next.next.val) {
                    int tmp = curr.next.val;
                    while (curr.next != null && curr.next.val == tmp) {
                        curr.next = curr.next.next;
                    }
                } else {
                    curr = curr.next;
                }
            }
            return tmpNode.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}