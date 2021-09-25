package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: zhangth
 * @CreateDate: 2021/9/15
 */
public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static ListNode arrayToNode(int[] nums) {
        ListNode head = null, curr = null;
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                head = new ListNode(nums[i]);
                curr = head;
            } else {
                ListNode node = new ListNode(nums[i]);
                curr.next = node;
                curr = curr.next;
            }
        }
        return head;
    }

    public static void nodeToArray(ListNode head) {
        List result = new ArrayList();
        ListNode curr = head;
        while (curr != null) {
            result.add(curr.val);
            curr = curr.next;
        }
        System.out.println(Arrays.toString(result.toArray()));
    }
}