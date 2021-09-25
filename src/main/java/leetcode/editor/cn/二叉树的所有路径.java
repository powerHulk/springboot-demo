package leetcode.editor.cn;

//给你一个二叉树的根节点 root ，按 任意顺序 ，返回所有从根节点到叶子节点的路径。 
//
// 叶子节点 是指没有子节点的节点。 
// 
//
// 示例 1： 
//
// 
//输入：root = [1,2,3,null,5]
//输出：["1->2->5","1->3"]
// 
//
// 示例 2： 
//
// 
//输入：root = [1]
//输出：["1"]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点的数目在范围 [1, 100] 内 
// -100 <= Node.val <= 100 
// 
// Related Topics 树 深度优先搜索 字符串 二叉树 👍 584 👎 0

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 二叉树的所有路径 {
    public static void main(String[] args) {
        Solution solution = new 二叉树的所有路径().new Solution();

        Integer[] nums = new Integer[]{1, 2, 3, null, 5};
        TreeNode treeNode = TreeNode.arrayToTree(nums);
        List<String> list = solution.binaryTreePaths(treeNode);
        System.out.println(Arrays.toString(list.toArray()));
    }


    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    class Solution {
        public List<String> binaryTreePaths(TreeNode root) {
            List<String> result = new ArrayList<>();
            constructPath(root, new StringBuilder(""), result);
            return result;
        }

        private void constructPath(TreeNode root, StringBuilder path, List<String> result) {
            if (root == null) {
                return;
            }
            path = new StringBuilder(path);
            path.append(root.val);
            if (root.left == null && root.right == null) {
                result.add(path.toString());
                return;
            }
            path.append("->");
            constructPath(root.left, path, result);
            constructPath(root.right, path, result);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}