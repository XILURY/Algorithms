package algorithm;

/**
 * 动态规划算法举例 o-1背包问题
 */
public class Dynamic {
    public static void main(String[] args) {
        int[] weight = {1, 4, 3}; // 三个物品的重量
        int[] val = {1500, 3000, 2000}; // 三个物品的价值
        int capacity = 4; // 背包容量
        int numCounter = val.length; // 获取物品的数量（有几个东西）
        int[][] table = new int[numCounter + 1][capacity + 1]; // 建表
        int[][] path = new int[numCounter + 1][capacity + 1]; // 记录放入的商品情况

        // 动态规划处理
        for (int i = 1; i < table.length; i++) { // 从第一个物品开始 遍历 (table的列是物品)
            for (int j = 1; j < table[0].length; j++) { // table的行是重量（包的容量）
                if (weight[i-1] > j) { //如果第i个物品的重量比包的容量大
                    table[i][j] = table[i-1][j]; //直接使用上一个单元格的装入策略
                } else {
//                    table[i][j]=Math.max(table[i-1][j], val[i-1]+table[i-1][j-weight[i-1]]);
                    // 为了记录放入的商品情况 写成if-else的形式
                    if (table[i-1][j] < val[i-1] + table[i-1][j-weight[i-1]]) { // 新加一个物品能使价值更大
                        table[i][j] = val[i-1] + table[i-1][j - weight[i-1]];
                        path[i][j] = 1;
                    } else {
                        table[i][j] = table[i-1][j]; // 新加一个物品价值并没有变大，仍然用上一个单元格的值
                    }
                }
            }
        }

        // 输出table
        System.out.println("table表为：");
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                System.out.printf("%8d", table[i][j]);
            }
            System.out.println();
        }

        System.out.println("path表为：");
        for (int i = 0; i < path.length; i++) {
            for (int j = 0; j < path[0].length; j++) {
                System.out.printf("%8d", path[i][j]);
            }
            System.out.println();
        }
        // 输出结果
        System.out.println("=======================================");
        int i = path.length - 1; // 行的最大下标
        int j = path[0].length - 1; // 列的最大下标
        while (i > 0 && j > 0) { // 从最后开始往前找
            if (path[i][j] == 1) {
                System.out.printf("第%d个商品放入包中\n", i);
                j = j - weight[i - 1]; // 找到一个之后减去该物品重量 继续找剩余重量中的放置方法
            }
            i--;
        }
    }
}
