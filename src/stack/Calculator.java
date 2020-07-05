package stack;
/**
 * 栈的应用（后缀表达式/逆波兰表达式）计算器
 * 从左至右扫描 将数字压入栈中，遇到运算符弹出两个数字进行运算
 */

import javax.print.DocFlavor;
import java.awt.desktop.SystemSleepEvent;
import java.sql.PreparedStatement;
import java.util.*;

public class Calculator {
    public static void main(String[] arg) {
//        System.out.println("请输入运算式：");
//        Scanner in = new Scanner(System.in);
//        String suffixExpression = in.next();
        String suffixExpression = "20*2-2+56+2-6*5";
        String suffixExpression1 = "(3+4)*5-6";
        List<String> list = converse(suffixExpression);
        System.out.println(list);
        System.out.println(suffixExpression + " 的后缀表达式为：" + pro(suffixExpression));
        System.out.println(suffixExpression + " = " + cal(pro(suffixExpression)) + "\n");

        List<String> list1 = converse(suffixExpression1);
        System.out.println(list1);
        System.out.println(suffixExpression1 + " 的后缀表达式为：" + pro(suffixExpression1));
        System.out.println(suffixExpression1 + " = " + cal(pro(suffixExpression1)));
    }

    //将中缀表达式转换成后缀表达式
    public static String pro(String suffixExpression) {
        List<String> list = converse(suffixExpression);
        Stack<String> s1 = new Stack<>();
        List<String> s2 = new ArrayList<>();

        for (String ele : list) {
            if (ele.matches("\\d+")) { // 如果是数 存入s2
                s2.add(ele);
            } else if (ele.equals("(")) { // 如果是（ 直接入符号栈
                s1.push(ele);
            } else if (ele.equals(")")) { // 如果是） 将s1中（上面的所有元素加入S2并将S1中（弹出
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();  // 将（弹出 消除小括号
            } else { // 当遇到运算符时 将当前运算符与栈顶运算符比较，若优先级小于等于栈顶，则将S1栈顶压入s2
                while (s1.size() != 0 && com(s1.peek()) >= com(ele)) {
                    s2.add(s1.pop());
                }
                s1.push(ele);
            }
        }
        while (s1.size() != 0) {
            s2.add(s1.pop());  // 注意是全部结束之后再将S1的剩余加入S2 而不是在循环里面
        }
        String str = "";
        for (String item : s2) {
            str = str + item + " ";
        }
        return str;
    }

    // 运算符优先级比较
    public static int com(String s) {
        switch (s) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            default:
                break;
        }
        return 0;
    }

    // 将字符串转换成链表 "(30+4)*5-6" =》[(, 30, +, 4, ), *, 5, -, 6]
    public static List<String> converse(String str) {
        List<String> list = new ArrayList<>();
        char[] ch = str.toCharArray();
        int i = 0;
        String temp;
        while (i < ch.length) {
            if (ch[i] == 43 || ch[i] == 45 || ch[i] == 42 || ch[i] == 47 || ch[i] == 40 || ch[i] == 41) { // ASCII码的+-*/()
                list.add("" + ch[i]); // 将字符转换成字符串
                i++;
            } else if (ch[i] > 47 && ch[i] < 58) {
                temp = "";
                while (i < ch.length && ch[i] > 47 && ch[i] < 58) {
                    temp = temp + ch[i];
                    i++;
                }
                list.add(temp);
            } else {
                i++;
            }
        }
        return list;
    }


    // 计算过程
    public static int cal(String suffixExpression) {
        String[] str = suffixExpression.split(" ");
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

