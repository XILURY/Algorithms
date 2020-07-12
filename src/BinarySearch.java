import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 二分查找
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] num = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        System.out.println(binarySearch(num, 0, num.length - 1, 1));
    }

    // 算法实现 找出所有的数下标
    public static List<Integer> binarySearch(int[] num, int left, int right, int value) {
        List<Integer> list = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        int mid = (left + right) / 2;
        if (left > right) {
            return new ArrayList<>();
        }
        if (value < num[mid]) {
            return binarySearch(num, left, mid - 1, value);
        } else if (value > num[mid]) {
            return binarySearch(num, mid + 1, right, value);
        } else {
            int temp = mid - 1;
            while (temp >= 0 && num[temp] == num[mid]) {
                stack.push(temp); // 将索引值存入栈 输出施从小到大输出 美观
                temp--;
            }
            while (!stack.empty()) {
                list.add(stack.pop());
            }
            list.add(mid);
            temp = mid + 1;
            while (temp < num.length && num[temp] == num[mid]) {
                list.add(temp);
                temp++;

            }
            return list;
        }
    }
}

//    /**
//     * 只查找一个数就停止
//     */
//    public static int binarySearch0(int[] num, int left, int right, int value) {
//        int mid = (left + right) / 2;
//        if (left > right) {
//            return -1;
//        }
//        if (value < num[mid]) {
//            return binarySearch0(num, left, mid - 1, value);
//        } else if (value > num[mid]) {
//            return binarySearch0(num, mid + 1, right, value);
//        } else {
//            return mid;
//        }
//    }
//}
