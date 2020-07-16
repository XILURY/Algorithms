package sort;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        int[] num = new int[]{4,6,8,5,9};
        heapSort(num);
        System.out.println(Arrays.toString(num));
    }

    /**
     * 堆排序
     * @param num 待排序的数组
     */
    public static void heapSort(int[] num) {
        int temp = 0;
        // Step1 将无序序列构造成一个大顶堆
        for (int i = num.length / 2 - 1; i >= 0; i--) { // num.length/2-1为第一个非叶子节点索引
            adjustHeap(num,i,num.length);
        }

        // Step2 将堆顶元素与末尾元素进行交换
        for(int j = num.length-1;j>0;j--) {
            temp = num[j];
            num[j] = num[0];
            num[0] = temp; // 交换元素 首末交换

            // 交换完之后，再将序列变成大顶堆
            adjustHeap(num,0,j);
        }
    }

    /**
     * 将以 i 对应的非叶子节点的树调整为大顶堆
     *
     * @param num    待调整的数组
     * @param i      表示非叶子节点在数组中的索引
     * @param length 表示参与比较的数组长度
     */
    public static void adjustHeap(int[] num, int i, int length) {
        int temp = num[i]; // 先取出待调整的非叶子节点的值/权

        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) { // k是i的左子节点
            if (k + 1 < length && num[k] < num[k + 1]) { // 比较左子节点和右子节点的大小，将K指向较大的那个
                k++;
            }
            if (num[k] > temp) { // 将非叶子节点i的值与其左右子节点中较大的值进行比较，并调整
                num[i] = num[k]; // 叶子节点的值大于非叶子节点 交换
                num[k] = temp; // 交换
                i = k; // 将i的指针变为k，继续判断下一个非叶子节点是否需要调整。
            } else {
                break;
            }
        }
    }
}
