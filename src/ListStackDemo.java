import java.util.Scanner;

/**
 * 链表模拟栈(头插法)
 */
public class ListStackDemo {
    public static void main(String[] arg) {
        // 测试
        ListStack stack = new ListStack();
        Scanner in = new Scanner(System.in);
        String key = "";
        boolean flag = true;

        while (flag) {
            System.out.println("show:显示栈");
            System.out.println("exit:退出程序");
            System.out.println("push:入栈");
            System.out.println("pop:出栈");
            System.out.println("请输入您的选择：");
            key = in.next();
            switch (key) {
                case "show":
                    try {
                        stack.showStack();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    flag = false;
                    break;
                case "push":
                    System.out.println("请输入一个数：");
                    Node node = new Node(in.nextInt());
                    stack.push(node);
                    break;
                case "pop":
                    try {
                        System.out.println("出栈的数据是：" + stack.pop());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;
            }
        }


    }

    static class ListStack {
        Node head = new Node(-1); // 初始化头节点
        Node temp = head.next;

        // 入栈
        public void push(Node node) {
            head.next = node;
            node.next = temp;
            temp = node;
        }

        // 出栈
        public int pop() {
            if (temp == null) {
                throw new RuntimeException("链表已经为空！\n");
            }
            int res = temp.getNo();
            temp = temp.next;
            return res;

        }

        // 显示栈的情况（从栈顶开始）
        public void showStack() {
            Node temp = head.next;
            if (head.next == null) {
                throw new RuntimeException("栈为空！\n");
            }
            while (temp != null) {
                System.out.println(temp.getNo());
                temp = temp.next;
            }
        }
    }

    // 定义节点
    static class Node {
        private int no;
        private Node next;

        public Node(Integer no) {
            this.no = no;
        }

        public int getNo() {
            return no;
        }
    }
}
