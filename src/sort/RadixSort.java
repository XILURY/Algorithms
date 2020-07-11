package sort;

import java.util.Arrays;

/**
 * 桶排序的实现
 */
public class RadixSort {
    public static void main(String[] args) {
//        int num[] = {8, 4, 5, 7, 1, 3, 6, 2};
//        int[] temp = new int[num.length];
//        radixSort(num);
//        System.out.println(Arrays.toString(num));

        // 桶排序效率很高，800万个数字排序时间不超过1s
        int[] num = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            num[i] = (int) (Math.random() * 800000);
        }

        long startTime0 = System.currentTimeMillis();
        radixSort(num);
        long endTime0 = System.currentTimeMillis();
        System.out.println("程序运行时间：" + (endTime0 - startTime0) + "ms");
    }

    // 算法实现
    public static void radixSort(int[] num) {
        // 寻找数组中最大数
        int max = num[0];
        for (int i = 0; i < num.length; i++) {
            if (num[i] > max) {
                max = num[i];
            }
        }
        // 得到最大数是几位数
        int maxLength = (max + "").length();

        int[][] bucket = new int[10][num.length]; // 建桶 10个桶分别对应0-9 每个桶中要存的数不知道有几个，初始化时用num.length
        int[] bucketElementCounts = new int[10]; // 初始化一个数组，里面存每一个桶中数字的个数
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            for (int j = 0; j < num.length; j++) {
                int digitOfElement = num[j] / n % 10; // 取出个位、十位、百位·····
                // 存进桶里
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = num[j];
                bucketElementCounts[digitOfElement]++; // 统计数字+1 （记录桶里有几个数字）
            }
            // 取数
            int index = 0;
            // 遍历每一桶
            for (int k = 0; k < 10; k++) {
                // 如果桶中有数据 取出放入原数组
                if (bucketElementCounts[k] != 0) {
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        num[index] = bucket[k][i];
                        index++;
                    }
                }
                // 取完数字后 将计数器归零 方便下一轮计算
                bucketElementCounts[k] = 0;
            }

        }

    }


}
