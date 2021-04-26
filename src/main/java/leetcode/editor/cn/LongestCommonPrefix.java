package leetcode.editor.cn;

//编写一个函数来查找字符串数组中的最长公共前缀。 
//
// 如果不存在公共前缀，返回空字符串 ""。 
//
// 
//
// 示例 1： 
//
// 
//输入：strs = ["flower","flow","flight"]
//输出："fl"
// 
//
// 示例 2： 
//
// 
//输入：strs = ["dog","racecar","car"]
//输出：""
//解释：输入不存在公共前缀。 
//
// 
//
// 提示： 
//
// 
// 0 <= strs.length <= 200 
// 0 <= strs[i].length <= 200 
// strs[i] 仅由小写英文字母组成 
// 
// Related Topics 字符串 
// 👍 1524 👎 0

public class LongestCommonPrefix {
    public static void main(String[] args) {
        Solution solution = new LongestCommonPrefix().new Solution();
        String[] strs = new String[]{"flower", "flow", "flowight"};
        String s = solution.longestCommonPrefix(strs);
        System.out.println(s);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String longestCommonPrefix(String[] strs) {


            int length = strs.length;
            if (length == 0) {
                return "";
            }
            String str = strs[0];

            for (int j = 0; j < str.length(); j++) {
                char c = str.charAt(j);
                for (int i = 1; i < length; i++) {
                    if (j > strs[i].length()-1 || c != strs[i].charAt(j)) {
                        return str.substring(0, j);
                    }
                }
            }
            return str;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}