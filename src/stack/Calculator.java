package stack;
/**
 * 栈的应用（后缀表达式/逆波兰表达式）计算器
 * 从左至右扫描 将数字压入栈中，遇到运算符弹出两个数字进行运算
 */

import javax.print.DocFlavor;
import java.sql.PreparedStatement;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Calculator {
    public static void main(String[] arg) {
//        System.out.println("请输入运算式：");
//        Scanner in = new Scanner(System.in);
//        String str = in.next();
//        System.out.println(str);
        String suffixExpression = "30 4 + 5 * 6 -";
        System.out.println("(30+4)*5-6 = " + cal(suffixExpression));
    }

    // 计算过程
    public static int cal(String suffixExpression) {
        String[] str = suffixExpression.split(" ");
        System.out.println(Arrays.toString(str));
        Stack<String> stack = new Stack<>();
        for (String ele : str) {
            if (ele.matches("\\d+")) {
                stack.push(ele);
            } else {
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                switch (ele) {
                    case "+":
                        res = num1 + num2;
                        break;
                    case "-":
                        res = num1 - num2;
                        break;
                    case "*":
                        res = num1 * num2;
                        break;
                    case "/":
                        res = num1 / num2;
                        break;
                    default:
                        break;
                }
                stack.push(String.valueOf(res));
            }
        }
        return Integer.parseInt(stack.pop());
    }


}

