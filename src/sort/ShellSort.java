package sort;

import java.util.Arrays;

/**
 * 希尔排序
 */
public class ShellSort {
    public static void main(String[] arg) {
        int[] num = new int[]{8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        System.out.println(Arrays.toString(shellSort(num)));


//        int[] num = new int[80000];
//        for (int i = 0; i < 80000; i++) {
//            num[i] = (int) (Math.random() * 800000);
//        }
//        long startTime0 = System.currentTimeMillis();
//        shellSort(num);
//        long endTime0 = System.currentTimeMillis();
//        System.out.println("程序运行时间：" + (endTime0 - startTime0) + "ms");
    }

    // 交换式希尔排序 效率相对于插入排序并没有提高 反而因为频繁交换数字而降低
//    public static int[] shellSort(int[] num) {
////        int temp = 0;
////        for (int gap = num.length / 2; gap > 0; gap /= 2) {
////            for (int i = gap; i < num.length; i++) {
////                for (int j = i - gap; j >= 0; j -= gap) {
////                    if (num[j] > num[j + gap]) {
////                        temp = num[j];
////                        num[j] = num[j + gap];
////                        num[j + gap] = temp;
////                    }
////                }
////            }
////        }
////        return num;
////    }


    // 移位式希尔排序
//    public static int[] shellSort(int[] num) {
//        for (int gap = num.length / 2; gap > 0; gap /= 2) { //切割数组终止条件
//            for (int i = gap; i < num.length; i++) { // 遍历切割的数组
//                int j = i;
//                int temp = num[j];
//                if (num[j] < num[j - gap]) {
//                    while (j - gap >= 0 && temp < num[j - gap]) {
//                        num[j] = num[j - gap];
//                        j = j - gap;
//                    }
//                    num[j] = temp;
//                }
//            }
//        }
//        return num;
//    }

    // 移位式希尔排序
    public static int[] shellSort(int[] num) {
        int insertValue = 0;
        int insertIndex = 0;
        for (int gap = num.length / 2; gap > 0; gap /= 2) { //切割数组终止条件
            for (int i = 0; i < num.length; i++) { // 遍历切割的数组
                for (int j = i; j < num.length; j += gap) {
                    insertValue = num[j];
                    if (j + gap < num.length && insertValue > num[j + gap]) {
                        num[j] = num[j + gap];
                        insertIndex = j + gap;
                    }
                }
                num[insertIndex] = insertValue;
            }
        }
        return num;
    }
}
