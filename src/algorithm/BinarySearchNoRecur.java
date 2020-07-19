package algorithm;

/**
 * 二分查找法 非递归
 */
public class BinarySearchNoRecur {
    public static void main(String[] args) {
        int[] num = {1, 2, 5, 6, 8, 9, 15, 19};
//        searchValue(num,5);
        System.out.println(searchValue(num, -2));
    }

    // 二分查找
    public static int searchValue(int[] num, int value) {
        int left = 0;
        int right = num.length - 1;
        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (value < num[mid]) { // 在左边
                right = mid - 1;
            } else if (value > num[mid]) { // 在右边
                left = mid + 1;
            } else {
                return mid; // 找到了
            }
        }
        return -1; // 没有找到
    }


}
