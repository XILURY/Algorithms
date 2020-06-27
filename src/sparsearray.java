/*
当一个数组中大部分元素为同一个值时可以采用稀疏数组来保存该数组，从而缩小程序的规模
洗漱数组记录原数组 一共 有多少行，有多少个不同的数值

二维数组转稀疏数组的思路
    1.遍历原始的二维数组，得到有效数据的个数sum
    2.根据sum就可以创建稀疏数组 sparseArr int[sum＋1]［3］
    3.将二维数组的有效数据数据存入到稀疏数组
稀疏数组转原始的二维数组的思路
    1.先读取稀疏数组的第一行，根据第一行的数据创建原始的二维数组，比如上面的 chessArr2＝int［11］［11］
    2.在读取稀疏数组后几行的数据，并赋给原始的二维数组即可
* */
public class sparsearray {
    public static void main(String[] args) {
        // 创建原始二维数组
        int chessArr[][] = new int[11][11];
        chessArr[2][3] = 1;
        chessArr[4][4] = 2;
        chessArr[4][5] = 1;
        chessArr[1][4] = 2;
        // 输出原始二维数组
        System.out.println("原始数组为：");
        for (int[] row : chessArr) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        // 计算有值的个数
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr[i][j] != 0) {
                    sum++;
                }
            }
        }

        // 创建稀疏数组
        int sparseArray[][] = new int[sum + 1][3];
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;

        int count = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr[i][j] != 0) {
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = chessArr[i][j];
                }
            }
        }

        // 输出稀疏数组
        System.out.println("稀疏数组为：");
        for (int i = 0; i < sparseArray.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArray[i][0], sparseArray[i][1], sparseArray[i][2]);
        }

        // 将稀疏数组恢复成原数组
        int m = sparseArray[0][0];
        int n = sparseArray[0][1];  // 获取行列值
        int chessA2[][] = new int[m][n];
        for(int i=1;i<sparseArray.length;i++){
            chessA2[sparseArray[i][0]][sparseArray[i][1]] =sparseArray[i][2];
        }
        System.out.println("恢复成原始数组为：");
        for (int[] row : chessA2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

    }
}
