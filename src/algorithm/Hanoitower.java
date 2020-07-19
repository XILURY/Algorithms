package algorithm;

import java.sql.SQLOutput;

/**
 * 分治算法 完成汉诺塔游戏
 */
public class Hanoitower {
    public static void main(String[] args) {
        hanoiTower(5,'a','b','c');

    }

    // 分治处理
    public static void hanoiTower(int num, char a, char b, char c) {
        // 如果只有一个盘 直接移动
        if (num == 1) {
            System.out.println("第1个盘从" + a + "->" + c);
        }else { // n>=2时
            // 将最上面的所有盘移动到B，借助C
            hanoiTower(num-1,a,c,b);
            // 把最下面的大盘移动到C
            System.out.println("第"+num+"个盘从"+a+"->"+c);
            // 把B盘上的所有盘再移动到C盘，借助A
            hanoiTower(num-1,b,a,c);
        }
    }
}
