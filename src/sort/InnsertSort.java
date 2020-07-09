package sort;

import java.util.Arrays;

/**
 * 插入排序
 */
public class InnsertSort {
    public static void main(String[] arg) {
//        int[] num = new int[]{9, 6, 3, 2, 5, 8, 7, 4, 1, 0};
//        insertSort(num);
//        System.out.println(Arrays.toString(insertSort(num)));

        int[] num = new int[80000];
        for (int i = 0; i < 80000; i++) {
            num[i] = (int) (Math.random() * 800000);
        }
        long startTime0 = System.currentTimeMillis();
        insertSort(num);
        long endTime0 = System.currentTimeMillis();
        System.out.println("优化前程序运行时间：" + (endTime0 - startTime0) + "ms");
    }

    public static int[] insertSort(int[] num) {
        for (int i = 1; i < num.length; i++) {
            int insertValue = num[i]; // 获取待插入的值
            int insertIndex = i;
            for (int j = i - 1; j >= 0 && insertValue < num[j]; j--) { // 有序数组从后往前遍历 j为插入的下标
                // 待插入的值小于比较值 就插入(小于改成大于就能实现从大到小排序)
                num[j + 1] = num[j];
                insertIndex = j;
            }
            if (insertIndex != i) { // 判断是否需要插值
                num[insertIndex] = insertValue;
            }
//            System.out.println(Arrays.toString(num));
        }
        return num;
    }
}
