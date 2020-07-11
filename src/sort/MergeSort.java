package sort;

import java.util.Arrays;

/**
 * 归并算法
 */
public class MergeSort {
    public static void main(String[] args) {
        int num[] = {8, 4, 5, 7, 1, 3, 6, 2};
        int[] temp = new int[num.length];
        mergeSort(num, 0, num.length - 1, temp);
        System.out.println(Arrays.toString(num));
    }

    // 算法实现
    public static void mergeSort(int[] num, int left, int right, int[] temp) {
        // 分
        if (left < right) { //设定递归结束条件
            int mid = (left + right) / 2; // 中间索引
            mergeSort(num, left, mid, temp); // 向左递归分解
            mergeSort(num, mid + 1, right, temp); // 向右递归分解
            // 治 && 和
            merge(num, left, mid, right, temp);
        }
    }

    // 治 && 合并数组
    public static void merge(int[] num, int left, int mid, int right, int[] temp) {
        int i = left; // 初始化i，左边有序序列的初始索引；
        int j = mid + 1; // 初始化j，右边有序序列初始化索引；
        int t = 0; // 指向temp数组当前索引

        // 比较填充数组 直到左右两个有序数组有一个遍历完
        while (i <= mid && j <= right) { // 如果左边数组中的数小于右边数组中的数，则添加左边的
            if (num[i] <= num[j]) {
                temp[t] = num[i];
                t += 1;
                i += 1;
            } else { //如果右边数组中的数小于左边数组中的数，则添加右边的
                temp[t] = num[j];
                t += 1;
                j += 1;
            }
        }

        // 有一个有序数组已经遍历完 则将另一个有序数组中剩余的数字添加到temp中
        while (i <= mid) {  // 左边的有序数组有剩余
            temp[t] = num[i];
            t += 1;
            i += 1;
        }
        while (j <= right) { // 右边的有序数组有剩余
            temp[t] = num[j];
            t += 1;
            j += 1;
        }

        // 拷贝
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            num[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }
    }
}
