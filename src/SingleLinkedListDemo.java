import java.awt.desktop.SystemSleepEvent;
import java.util.Stack;

/*
 * 单列表的应用 水浒英雄的插入
 * 1）按照插入顺序
 * 2）按照排名
 * 3）更新节点
 * 4）删除节点
 */
public class SingleLinkedListDemo {
    public static void main(String[] arg) {
        // 测试
        // 创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "公孙胜", "入云龙");
        HeroNode hero5 = new HeroNode(5, "关胜", "大刀");

        // 创建链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        singleLinkedList.add(hero4);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero5);

        singleLinkedList.addBySort(hero5);
        singleLinkedList.addBySort(hero2);
        singleLinkedList.addBySort(hero4);
        singleLinkedList.addBySort(hero3);
        singleLinkedList.addBySort(hero1);

//        HeroNode newHero = new HeroNode(4, "关小胜", "大大刀");
//        singleLinkedList.update(newHero);

//        singleLinkedList.del(5);
        singleLinkedList.showList();

//        System.out.println("链表有效个数为：" + singleLinkedList.count());
//
//        // 倒数第K个节点为：
//        System.out.println(singleLinkedList.find(singleLinkedList,3));

        // 翻转链表
//        System.out.println("翻转之后的链表为：");
//        singleLinkedList.getReverse();
//        singleLinkedList.showList();

        // 翻转输出
        singleLinkedList.reversePrint();


    }

    static class SingleLinkedList {
        // 初始化一个头节点，头节点不动 方便遍历整个链表
        HeroNode head = new HeroNode(0, " ", " ");

        // 不考虑编号顺序 添加到节点
        private void add(HeroNode heroNode) {
            HeroNode temp = head;
            // 将节点指向最后 找到链表的最后
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = heroNode;
        }

        // 按编码顺序 添加到节点
        private void addBySort(HeroNode heroNode) {
            HeroNode temp = head;
            boolean flag = false; // 添加的编号是否存在 默认不存在
            while (true) {
                if (temp.next == null) { // 链表为空 节点不用移
                    break;
                }
                if (temp.next.no > heroNode.no) { // 找到要插入的位置 节点移动停止
                    break;
                } else if (temp.next.no == heroNode.no) { // 要添加的编号存在了 节点移动停止
                    flag = true;
                    break;
                }
                temp = temp.next;
            }
            if (flag == true) {
                System.out.printf("准备插入的编号 %d 已经存在了,不能加入!\n", heroNode.no);
            } else {
                // 重点理解部分 先将要插入的节点的下一节点指向现在的temp的下一节点 然后将heroNode赋为temp的下一个节点
                heroNode.next = temp.next;
                temp.next = heroNode;
            }
        }

        // 根据编号修改节点信息
        private void update(HeroNode newHeroNode) {
            HeroNode temp = head;
            boolean flag = false;
            if (head.next == null) {
                System.out.println("列表为空，不能修改！");
                return;  // return 很重要 如果列表为空也就不必执行下面的步骤了
            }
            while (true) {
                if (temp.next == null) {
                    break;
                }
                if (temp.next.no == newHeroNode.no) {
                    flag = true;
                    break;
                }
                temp = temp.next;
            }
            if (flag) {
                temp.next.name = newHeroNode.name;
                temp.next.nickName = newHeroNode.nickName;
            } else
                System.out.println("没有该节点！");

        }

        // 删除节点
        public void del(int no) {
            HeroNode temp = head;
            if (head.next == null) {
                System.out.println("链表为空！无法删除");
            }
            boolean flag = false;
            while (true) {
                if (temp.next == null) {
                    break;
                }
                if (temp.next.no == no) {
                    flag = true;
                    break;
                }
                temp = temp.next;
            }
            if (flag) {
                temp.next = temp.next.next; // 重点理解 删除该节点只要没有节点指向它 就会被垃圾回收
            } else {
                System.out.printf("要删除的节点 %d 不存在！\n", no);
            }
        }

        // 显示整个链表（遍历）
        public void showList() {
            HeroNode temp = head;
            if (temp.next == null) {
                System.out.println("链表为空！");
                return;
            }
            while (temp.next != null) {
                temp = temp.next;
                System.out.println(temp);
            }
        }

        // 求单链表中有效节点个数
        public int count() {
            HeroNode temp = head;
            int count = 0;
            if (head.next == null) {
                // 链表为空
                return count;
            }
            while (temp.next != null) {
                temp = temp.next;
                count++;
            }
            return count;
        }

        // 查找列表中倒数第K个节点
        public HeroNode find(SingleLinkedList singleLinkedList, int k) {
            HeroNode temp = head;
            if (k > singleLinkedList.count()) {
                if (head.next == null)
                    System.out.println("链表为空！");
                else
                    System.out.println("输入的K超过可选范围！");
            } else {
                for (int i = 0; i < singleLinkedList.count() - k + 1; i++) {
                    temp = temp.next;
                }
            }
            return temp;
        }

        // 翻转链表（有一点难度）
        public void getReverse() {
            HeroNode temp = head.next;
            HeroNode next = null;
            HeroNode reverseNode = new HeroNode(0, " ", " ");
            if (head.next == null) {
                System.out.println("链表为空，不能翻转！");
            }
            if (head.next.next == null) { //链表中只有一个节点 直接返回就行
                System.out.println("只有一个节点不用翻转");
            }
            // 遍历原来的链表，每遍历一个节点就取出，将其放在reverseNode的最前端 并且将其下节点指向reverseNode头部
            while (temp != null) {
                next = temp.next; // 保存当前节点的下一节点
                temp.next = reverseNode.next; // 将当前节点的下一节点指向翻转链表的最前端
                reverseNode.next = temp; // 将当前节点存入翻转链表的最前端
                temp = next; // 让temp后移
            }
            head.next = reverseNode.next; // 实现链表翻转
        }

        // 从未到头打印链表
        public void reversePrint(){
            HeroNode temp = head.next;
            if(head.next == null){
                System.out.println("链表为空！");
            }
            Stack<HeroNode> stack = new Stack<>();
            while(temp.next != null){
                stack.push(temp);
                temp = temp.next;
            }
            while(stack.size()>0){
                System.out.println(stack.pop());
            }
        }
    }



    // 定义HeroNode，每一个HeroNode对象就是一个节点
    static class HeroNode {
        private int no;
        private String name;
        private String nickName;
        private HeroNode next;

        // 构造器
        public HeroNode(int no, String name, String nickName) {
            this.no = no;
            this.name = name;
            this.nickName = nickName;
        }

        // 重写toString 方便输出
        @Override
        public String toString() {
            return "HeroNode[no = " + no + ", name=" + name + ", nickname=" + nickName + "]";
        }


    }
}
