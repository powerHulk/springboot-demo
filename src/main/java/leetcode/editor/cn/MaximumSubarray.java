package leetcode.editor.cn;

//给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
//输出：6
//解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [1]
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：nums = [0]
//输出：0
// 
//
// 示例 4： 
//
// 
//输入：nums = [-1]
//输出：-1
// 
//
// 示例 5： 
//
// 
//输入：nums = [-100000]
//输出：-100000
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 3 * 104 
// -105 <= nums[i] <= 105 
// 
//
// 
//
// 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。 
// Related Topics 数组 分治算法 动态规划 
// 👍 3070 👎 0

public class MaximumSubarray {
    public static void main(String[] args) {
        Solution solution = new MaximumSubarray().new Solution();
        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int i = solution.maxSubArray(nums);
        System.out.println(i);

//        int i = solution.solutionFibonacci2(6);
//        System.out.println(i);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxSubArray(int[] nums) {
//            int pre = 0, maxAns = nums[0];
//            for (int x : nums) {
//                pre = Math.max(pre + x, x);
//                maxAns = Math.max(maxAns, pre);
//            }
            int ans = nums[0];
            int sum = 0;
            for(int num : nums) {
                if(sum > 0) {
                    sum += num;
                } else {
                    sum = num;
                }
                ans = Math.max(ans, sum);
            }
            return ans;
//            return maxAns;
        }

//        public int solutionFibonacci(int n) {
//            if (n == 0) {
//                return 0;
//            } else if (n == 1) {
//                return 1;
//            } else {
//                return solutionFibonacci(n - 1) + solutionFibonacci(n - 2);
//            }
//        }
//
//        public int solutionFibonacci2(int n) {
//            if (n == 0) {
//                return 0;
//            } else if (n == 1) {
//                return 1;
//            } else {
//                int result[] = new int[n + 1];
//                result[0] = 0;
//                result[1] = 1;
//                for (int i = 2; i <= n; i++) {
//                    result[i] = result[i - 1] + result[i - 2];
//                }
//                return result[n];
//            }
//        }
//leetcode submit region end(Prohibit modification and deletion)

    }
}