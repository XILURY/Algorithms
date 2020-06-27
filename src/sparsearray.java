import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

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
    public static void main(String[] args) throws IOException {
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
        /**
         *保存稀疏数组到txt
         */
        try {
            FileWriter os = new FileWriter("test.txt");
            for (int i = 0; i < sparseArray.length; i++) {
                for (int j = 0; j < sparseArray[0].length; j++) {
                    os.write(sparseArray[i][j] + "\t"); // writes the bytes
                }
                os.write("\n");
            }
            os.close();

        } catch (IOException e) {
            System.out.print("Exception");
        }
        // 控制台输出稀疏数组
        System.out.println("稀疏数组为：");
        for (int i = 0; i < sparseArray.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArray[i][0], sparseArray[i][1], sparseArray[i][2]);
        }

        // 将稀疏数组恢复成原数组
        /**
         *从文件读取数组 将读到的文件转换成二维数组
         */

        // 使用ArrayList来存储每行读取到的字符串
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            File file = new File("test.txt");
            InputStreamReader input = new InputStreamReader(new FileInputStream("test.txt"));
            BufferedReader bf = new BufferedReader(input);
            // 按行读取字符串
            String str;
            while ((str = bf.readLine()) != null) {
                arrayList.add(str);
            }
            bf.close();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对ArrayList中存储的字符串进行处理
        int length = arrayList.size();
        int width = arrayList.get(0).split("\t").length;
        int array[][] = new int[length][width];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                String s = arrayList.get(i).split("\t")[j];
                array[i][j] = Integer.parseInt(s);
            }
        }

        int m = array[0][0];
        int n = array[0][1];  // 获取行列值
        int chessA2[][] = new int[m][n];
        for (int i = 1; i < array.length; i++) {
            chessA2[array[i][0]][array[i][1]] = array[i][2];
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

