/**
 * Josephu问题：
 *设编号为1，2，… n的n个人围坐一圈，约定编号为k（1<=k<=n）的人从1开始报数，
 * 数到m 的那个人出列，它的下一位又从1开始报数，数到m的那个人又出列，依次类推，直到所有人出列为止，由此产生一个出队编号的序列。
 */
public class Josephu {
    public static void main(String[] arg) {
        // 测试
        CircleSingleLinkedList list = new CircleSingleLinkedList();

        list.add(5);
//
//        list.showList();

        list.countBoy(1, 2, 5);

    }

    // 创建环形单向链表
    static class CircleSingleLinkedList {
        private Node first = null; // 创建first节点 内容为空

        // 添加小孩节点，形成环形链表
        public void add(int num) { // num为要添加的小孩个数
            if (num < 1) {
                System.out.println("输入的值不正确");
                return;
            }
            Node cur = null; // 辅助指针
            for (int i = 1; i <= num; i++) {
                Node boy = new Node(i);
                if (i == 1) {
                    first = boy;  // 指向唯一的节点
                    first.setNext(first); // 将唯一的节点指向自己形成环
                    cur = first; // 让cur指向第一个小孩
                } else {
                    cur.setNext(boy); // 将cur指针指向新的boy
                    boy.setNext(first); // 构成环
                    cur = boy; // 将cur移动到boy
                }
            }
        }

        // 遍历环形链表
        public void showList() {
            if (first == null) {
                System.out.println("链表为空！");
                return;
            }
            System.out.println(first.getNo());
            Node temp = first.getNext();
            while (temp != first) {
                System.out.println(temp.getNo());
                temp = temp.getNext();
            }
        }


        // 根据用户输入计算出圈顺序

        /**
         * @param startNo   表示从第几个小孩开始数
         * @param boyNumber 表示数几下
         * @param nums      表示最初有几个小孩在圈中
         */
        public void countBoy(int startNo, int boyNumber, int nums) {
            if (first == null || startNo < 1 || startNo > nums) { // 链表为空，开始的小孩数字小于1，开始的编号大于总的小孩数
                System.out.println("输入参数有误！");
            }
            Node helper = first; // 定义一个辅助指针
            // 将 heleper指向first的前一个（即队末） 通过循环
            while (true) {
                if (helper.getNext() == first) {
                    break;
                }
                helper = helper.getNext();
            }
            // 小孩报数前先让first和helper移动k-1次（移动到开始报数的那个小孩位置）
            for (int j = 0; j < startNo - 1; j++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            // 开始出圈
            while (true) {
                if (helper == first) {
                    break;
                }
                // 开始数数
                for (int i = 0; i < boyNumber - 1; i++) {
                    helper = helper.getNext();
                    first = first.getNext();
                }
                // 出圈
                System.out.printf("小孩 %d 出圈\n", first.no);
                first = first.getNext();
                helper.setNext(first);
            }
            System.out.printf("留在圈里的小孩是：" + first.no);

        }

    }

    // 构造节点
    static class Node {
        private int no;
        private Node next;

        public Node(int no) {
            this.no = no;
        }

        public int getNo() {
            return no;
        }

        public Node getNext() {
            return next;
        }

        public void setNo(int no) {
            this.no = no;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
