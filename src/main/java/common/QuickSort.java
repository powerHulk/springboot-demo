package common;

import java.util.Arrays;

/**
 * @author: zhangth
 * @CreateDate: 2021/9/18
 */
public class QuickSort {

    public static void main(String[] args) {
        QuickSort quickSort=new QuickSort();
        int[] nums=new int[]{2,9,8,4,6};
        quickSort.quickSort(nums,0,nums.length-1);
        System.out.println(Arrays.toString(nums));
    }

    private void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int index = getIndex(arr, low, high);
            quickSort(arr, low, index - 1);
            quickSort(arr, index + 1, high);
        }
    }

    private int getIndex(int[] arr, int low, int high) {
        int tmp = arr[low];
        while (low < high) {
            while (low < high && arr[high] >= tmp) {
                high--;
            }
            arr[low] = arr[high];
            while (low < high && arr[low] <= tmp) {
                low++;
            }
            arr[high] = arr[low];

        }
        arr[low] = tmp;
        return low;
    }
}
