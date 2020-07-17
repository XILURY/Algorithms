package Tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {
    public static void main(String[] args) {
        int[] num = {13,7,8,3,29,6,1};
        Node root = createHuffmanTree(num);
        preOrder(root);
    }

    /**
     * 前序遍历二叉树
     * @param root 根节点
     */
    public static void preOrder(Node root) {
        if(root != null) {
            System.out.print(root.value+" ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    /**
     * 创建哈夫曼树
     * @param num 传入的数组
     * @return 返回哈夫曼树的root节点
     */
    public static Node createHuffmanTree(int[] num) {
        List<Node> nodeList = new ArrayList<>();
        // 将数组元素转换成Node
        for (int ele : num) {
            nodeList.add(new Node(ele));
        }

        // 链表中还有元素就一直重复 建树 删元素
        while (nodeList.size() > 1) {
            // 从小到大排序
            Collections.sort(nodeList);

            // 取出权值最小的两个节点
            Node leftNode = nodeList.get(0);
            Node rightNode = nodeList.get(1);
            // 组成一个新树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;

            // 从链表中删除处理完的数
            nodeList.remove(leftNode);
            nodeList.remove(rightNode);

            // 将parent加入链表中
            nodeList.add(parent);
        }
        // 返回哈夫曼树的root节点
        return nodeList.get(0);
    }

    /**
     * 创建节点
     * 实现Comparable接口
     */
    static class Node implements Comparable<Node> {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node[value = " + value + "]";
        }

        @Override
        public int compareTo(Node node) {
            // 表示从小到大排序
            return this.value - node.value;
        }

    }
}
