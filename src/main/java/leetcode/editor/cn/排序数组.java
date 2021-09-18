package leetcode.editor.cn;

//给你一个整数数组 nums，请你将该数组升序排列。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 输入：nums = [5,2,3,1]
//输出：[1,2,3,5]
// 
//
// 示例 2： 
//
// 输入：nums = [5,1,1,2,0,0]
//输出：[0,0,1,1,2,5]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 50000 
// -50000 <= nums[i] <= 50000 
// 
// Related Topics 数组 分治 桶排序 计数排序 基数排序 排序 堆（优先队列） 归并排序 👍 373 👎 0

import java.util.Arrays;

public class 排序数组 {
    public static void main(String[] args) {
        Solution solution = new 排序数组().new Solution();
        int[] nums = new int[]{5, 1, 1, 2, 0, 0};
        int[] result = solution.sortArray(nums);

        System.out.println(Arrays.toString(result));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] sortArray(int[] nums) {
//            bubbleSort(nums);
            quickSort(nums, 0, nums.length - 1);
            return nums;
        }

        /**
         * 快速排序
         */
        private void quickSort(int[] arr, int low, int high) {
            if (low < high) {
                // 找寻基准数据的正确索引
                int index = getIndex(arr, low, high);
                // 进行迭代对index之前和之后的数组进行相同的操作使整个数组变成有序
                quickSort(arr, low, index - 1);
                quickSort(arr, index + 1, high);
            }

        }

        private int getIndex(int[] arr, int low, int high) {
            // 基准数据
            int tmp = arr[low];
            while (low < high) {
                // 当队尾的元素大于等于基准数据时,向前挪动high指针
                while (low < high && arr[high] >= tmp) {
                    high--;
                }
                // 如果队尾元素小于tmp了,需要将其赋值给low
                arr[low] = arr[high];
                // 当队首元素小于等于tmp时,向前挪动low指针
                while (low < high && arr[low] <= tmp) {
                    low++;
                }
                // 当队首元素大于tmp时,需要将其赋值给high
                arr[high] = arr[low];

            }
            // 跳出循环时low和high相等,此时的low或high就是tmp的正确索引位置
            // 由原理部分可以很清楚的知道low位置的值并不是tmp,所以需要将tmp赋值给arr[low]
            arr[low] = tmp;
            return low; // 返回tmp的正确位置
        }

        /**
         * 冒泡排序
         *
         * @param nums
         * @return
         */

        public int[] bubbleSort(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                for (int j = 0; j < i; j++) {
                    if (nums[i] < nums[j]) {
                        int tmp = nums[i];
                        nums[i] = nums[j];
                        nums[j] = tmp;
                    }
                }
            }
            return nums;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}