package leetcode.editor.cn;

//给你二叉树的根节点 root ，返回它节点值的 前序 遍历。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,null,2,3]
//输出：[1,2,3]
// 
//
// 示例 2： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：root = [1]
//输出：[1]
// 
//
// 示例 4： 
//
// 
//输入：root = [1,2]
//输出：[1,2]
// 
//
// 示例 5： 
//
// 
//输入：root = [1,null,2]
//输出：[1,2]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目在范围 [0, 100] 内 
// -100 <= Node.val <= 100 
// 
//
// 
//
// 进阶：递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 深度优先搜索 二叉树 👍 644 👎 0

import java.util.ArrayList;
import java.util.List;

public class 二叉树的前序遍历 {
    public static void main(String[] args) {
        Solution solution = new 二叉树的前序遍历().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        List<Integer> result = new ArrayList<>();

        public List<Integer> preorderTraversal(TreeNode root) {
            first(root);
            return result;
        }

        public void first(TreeNode root) {
            if (root == null) {
                return;
            }
            result.add(root.val);
            first(root.left);
            first(root.right);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}