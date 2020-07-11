package sort;

import java.util.Arrays;

/**
 * 快速排序
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] num = new int[]{9, 6, 3, 2, 5, 8, 7, 4, 1, 0, -3, -2};
        quickSort(num, 0, num.length - 1);
        System.out.println(Arrays.toString(num));
    }

    // 快速排序
    public static void quickSort(int[] arr, int low, int high) {
        int i, j, temp, t;
        if (low > high) {
            return;
        }
        i = low;
        j = high;
        //temp就是基准位（以首位为基准）
        temp = arr[low];

        while (i < j) {
            //先看右边，依次往左递减 直到找到要交换的数字为止
            while (temp <= arr[j] && i < j) {
                j--;
            }
            //再看左边，依次往右递增 直到找到要交换的数字为止
            while (temp >= arr[i] && i < j) {
                i++;
            }
            //开始交换
            t = arr[j];
            arr[j] = arr[i];
            arr[i] = t;
        }
        //最后将基准为与i和j相等位置的数字交换（循环完成后 i = j）
        arr[low] = arr[i];
        arr[i] = temp;
        //递归调用左半数组
        quickSort(arr, low, j - 1);
        //递归调用右半数组
        quickSort(arr, j + 1, high);
    }
}
