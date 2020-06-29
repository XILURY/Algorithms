import javax.swing.*;
import java.net.PortUnreachableException;

/*
 * 单列表的应用 水浒英雄的插入
 * 1）按照插入顺序
 * 2）按照排名
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
        private void update(HeroNode newHeroNode){
            HeroNode temp = head;
            boolean flag = false;
            if(head.next == null){
                System.out.println("列表为空，不能修改！");
                return;  // return 很重要 如果列表为空也就不必执行下面的步骤了
            }
            while(true){
                if(temp.next == null){
                    break;
                }
                if(temp.next.no == newHeroNode.no){
                    flag = true;
                    break;
                }
                temp = temp.next;
            }
            if(flag){
                temp.next.name = newHeroNode.name;
                temp.next.nickName = newHeroNode.nickName;
            }else
                System.out.println("没有该节点！");

        }

        // 删除节点
        public void del(int no){
            HeroNode temp = head;
            if(head.next == null){
                System.out.println("链表为空！无法删除");
            }
            boolean flag = false;
            while (true){
                if(temp.next == null){
                    break;
                }
                if(temp.next.no == no){
                    flag = true;
                    break;
                }
                temp = temp.next;
            }
            if(flag){
                temp.next = temp.next.next; // 重点理解 删除该节点只要没有节点指向它 就会被垃圾回收
            }else{
                System.out.printf("要删除的节点 %d 不存在！",no);
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
