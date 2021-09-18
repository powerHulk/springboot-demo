package leetcode.editor.cn;

//从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。 
//
// 
//
// 例如: 
//给定二叉树: [3,9,20,null,null,15,7], 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
// 
//
// 返回： 
//
// [3,9,20,15,7]
// 
//
// 
//`
// 提示： 
//
// 
// 节点总数 <= 1000 
// 
// Related Topics 树 广度优先搜索 二叉树 👍 126 👎 0

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 从上到下打印二叉树 {
    public static void main(String[] args) {
        Solution solution = new 从上到下打印二叉树().new Solution();

        Integer[] nums = new Integer[]{3, 9, 20, null, null, 15, 7};
        TreeNode treeNode = TreeNode.arrayToTree(nums);
        int[] ints = solution.levelOrder(treeNode);
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i]);
        }
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    class Solution {
        public int[] levelOrder(TreeNode root) {
            if (root == null) {
                return new int[0];
            }
            List<Integer> list = new ArrayList<>();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            int[] result = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                result[i] = list.get(i);
            }
            return result;
        }

        /**
         * 通过list
         *
         * @param root
         * @return
         */
        public int[] levelOrderByList(TreeNode root) {
            if (root == null) {
                return new int[0];
            }
            List<Integer> list = new ArrayList<>();
            List<TreeNode> treeNodeList = new ArrayList<>();
            treeNodeList.add(root);
            while (!treeNodeList.isEmpty()) {
                List<TreeNode> tmpList = new ArrayList<>();
                for (TreeNode treeNode : treeNodeList) {
                    list.add(treeNode.val);

                    if (treeNode.left != null) {
                        tmpList.add(treeNode.left);
                    }
                    if (treeNode.right != null) {
                        tmpList.add(treeNode.right);
                    }
                }
                treeNodeList = tmpList;
            }
            int[] result = new int[list.size()];
            for (int i = 0; i < list.size(); i++) {
                result[i] = list.get(i);
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}