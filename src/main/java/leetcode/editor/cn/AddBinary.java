package leetcode.editor.cn;

//给你两个二进制字符串，返回它们的和（用二进制表示）。 
//
// 输入为 非空 字符串且只包含数字 1 和 0。 
//
// 
//
// 示例 1: 
//
// 输入: a = "11", b = "1"
//输出: "100" 
//
// 示例 2: 
//
// 输入: a = "1010", b = "1011"
//输出: "10101" 
//
// 
//
// 提示： 
//
// 
// 每个字符串仅由字符 '0' 或 '1' 组成。 
// 1 <= a.length, b.length <= 10^4 
// 字符串如果不是 "0" ，就都不含前导零。 
// 
// Related Topics 数学 字符串 
// 👍 594 👎 0

public class AddBinary {
    public static void main(String[] args) {
        Solution solution = new AddBinary().new Solution();
        System.out.println(solution.addBinary("11","1"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String addBinary(String a, String b) {

            String result = "";
            int indexa = a.length() - 1;
            int indexb = b.length() - 1;
            int flag = 0;
            while (indexa >= 0 || indexb >= 0) {
                int sum = 0;
                if (indexa >= 0) {
                    sum += a.charAt(indexa)-48;
                }
                if (indexb >= 0) {
                    sum += b.charAt(indexb)-48;
                }
                sum += flag;
                if (sum / 2 > 0) {
                    flag = 1;
                } else {
                    flag = 0;
                }
                result = sum % 2 + result;

                indexa--;
                indexb--;
            }
            if(flag>0){
                result = flag + result;
            }

            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}












