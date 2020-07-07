package sort;

import java.util.Arrays;

/**
 * 冒泡排序的实现
 */
public class BubbleSort {
    public static void main(String[] arg) {
        int[] num = new int[80000];
        for (int i = 0; i < 80000; i++) {
            num[i] = (int) (Math.random() * 800000);
        }
        int[] num1 = new int[80000];
        for (int i = 0; i < 80000; i++) {
            num1[i] = num[i];
        }
        long startTime0 = System.currentTimeMillis();
        bubbleSort0(num);
        long endTime0 = System.currentTimeMillis();
        System.out.println("优化前程序运行时间：" + (endTime0 - startTime0) + "ms");

        long startTime = System.currentTimeMillis();
        bubbleSort(num1);
        long endTime = System.currentTimeMillis();
        System.out.println("优化后程序运行时间：" + (endTime - startTime) + "ms");
    }

    // 冒泡排序（优化后）
    public static int[] bubbleSort(int[] num) {
        int temp = 0;
        boolean flag = true;
        for (int k = 0; k < num.length - 1; k++) {
            for (int i = 0; i < num.length - 1 - k; i++) {
                if (num[i] > num[i + 1]) {
                    flag = false;
                    temp = num[i];
                    num[i] = num[i + 1];
                    num[i + 1] = temp;
                }
            }
            if (flag) {
                break; // flag为真，说明没有进行比较，可以提前结束冒泡排序
            } else {
                flag = true; // 重置flag进行下一轮冒泡
            }
        }
        return num;
    }

    // 冒泡排序（没优化）
    public static int[] bubbleSort0(int[] num) {
        int temp = 0;
        for (int k = 0; k < num.length - 1; k++) {
            for (int i = 0; i < num.length - 1 - k; i++) {
                if (num[i] > num[i + 1]) {
                    temp = num[i];
                    num[i] = num[i + 1];
                    num[i + 1] = temp;
                }
            }
        }
        return num;
    }
}
