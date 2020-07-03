package stack;
import java.util.Scanner;

/**
 * 数组模拟栈
 */
public class ArrayStackDemo {
    public static void main(String[] arg) {
        // 测试
        Stack stack = new Stack(4);
        Scanner in = new Scanner(System.in);
        String key = "";
        boolean flag = true;

        while(flag){
            System.out.println("show:显示栈");
            System.out.println("exit:退出程序");
            System.out.println("push:入栈");
            System.out.println("pop:出栈");
            System.out.println("请输入您的选择：");
            key = in.next();
            switch (key){
                case "show":
                    stack.showStack();
                    break;
                case "exit":
                    flag = false;
                    break;
                case "push":
                    System.out.println("请输入一个数：");
                    int n = in.nextInt();
                    stack.push(n);
                    break;
                case "pop":
                    try {
                        System.out.println("出栈的数据是："+stack.pop());
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;
            }
        }


    }

    static class Stack {
        private int maxSize;
        private int[] stack;
        private int top = -1; // top表示栈顶 初始为-1

        public Stack(int maxSize) {
            this.maxSize = maxSize;
            stack = new int[this.maxSize];
        }

        // 入栈
        public void push(int n) {
            if (top == maxSize - 1) { // 判断栈是否满了
                System.out.println("栈满，无法添加数据");
                return; // 避免执行下面的程序 报错
            }
            stack[++top] = n;
        }

        // 出栈
        public int pop() {
            if (top == -1) {
                throw new RuntimeException("栈为空！无法出栈");
            }
            return stack[top--];
        }

        // 显示栈的情况（从栈顶开始）
        public void showStack() {
            if (top == -1) {
                System.out.println("栈为空！");
            }
            for (int i = top; i >= 0; i--) {
                System.out.printf("stack[%d] = %d\n", i, stack[i]);
            }
        }
    }
}
