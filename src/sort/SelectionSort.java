package sort;

import java.util.Arrays;

/**
 * 选择排序的实现
 */
public class SelectionSort {
    public static void main(String[] arg) {
        int[] num = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        System.out.println(Arrays.toString(selectionSort(num)));
//        int[] num = new int[80000];
//        for (int i = 0; i < 80000; i++) {
//            num[i] = (int) (Math.random() * 800000);
//        }
//
//        long startTime0 = System.currentTimeMillis();
//        selectionSort(num);
//        long endTime0 = System.currentTimeMillis();
//        System.out.println("优化前程序运行时间：" + (endTime0 - startTime0) + "ms");
    }

    // 选择排序
    public static int[] selectionSort(int[] num) {
        for (int i = 0; i < num.length - 1; i++) {
            int min = num[i]; // 默认当前第一个数是最小的数
            int minIndex = i; // 默认当前数是最小
            for (int j = i + 1; j < num.length; j++) {
                if (min > num[j]) { // 比较寻找最小的数 并记录其下标
                    min = num[j];
                    minIndex = j;
                }
            }
            // 比较完之后交换i
            if (minIndex != i) {
                num[minIndex] = num[i];
                num[i] = min;

            }
        }
        return num;
    }
}
