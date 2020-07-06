public class Queen8 {
    // 理论上应该创建一个二维数组来表示棋盘，但是实际上可以通过算法，用一个一维数组即可解决问题。
    // arr[8] = {0 , 4, 7, 5, 2, 6, 1, 3} 对应arr下标第几个皇后，val表示位置。
    int max = 8;
    int[] array = new int[max];
    static int count = 0;
    static int judgeCount = 0;
    public static void main(String[] arg) {
        Queen8 queen8 = new Queen8();
        queen8.check(0);
        System.out.printf("一共有%d种解法\n",count);
        System.out.printf("一共判断%d",judgeCount);


    }

    // 放置皇后
    private void check(int n) {
        if (n == 8) { // 如果已经放了8个 就完成 （退出递归条件）
            print();
            return;
        }
        // 依次放入皇后 判断是否冲突
        for (int i = 0; i < max; i++) {
            // 放置皇后（从第一列i=0开始）
            array[n] = i;
            // 判断当前是否冲突
            if (judge(n)) { // 如果不冲突
                check(n + 1);
            }
            // 如果冲突 继续循环
        }
    }

    // 判断是否满足条件
    private boolean judge(int n) { // n为第几个皇后
        judgeCount++;
        for (int i = 0; i < n; i++) { // 判断n之前的几个皇后 array的val即为皇后的位置（列方向）
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    // 打印数组
    private void print() {
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "  ");
        }
        System.out.println();
    }
}
