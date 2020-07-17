package Tree;

public class BinarySortTree {
    public static void main(String[] args) {
        int[] num = {7, 3, 10, 12, 5, 1, 9};
        BinaryTree binaryTree = new BinaryTree();
        for (int i : num) {
            binaryTree.add(new Node(i));
        }
        binaryTree.add(new Node(2));
        System.out.println("原为：");
        binaryTree.infixOrder(binaryTree.root);

        binaryTree.del(1);
        System.out.println("\n删除后为：");
        binaryTree.infixOrder(binaryTree.root);
    }

    /**
     * 创建二叉排序树
     */
    static class BinaryTree {
        private Node root;

        // 添加节点
        public void add(Node node) {
            if (root == null) {
                root = node;
            } else {
                root.add(node);
            }
        }

        // 查找要删除的节点
        public Node search(int value) {
            if (root == null) {
                return null;
            } else {
                return root.search(value);
            }
        }

        // 查找要删除的父节点
        public Node searchParent(int value) {
            if (root == null) {
                return null;
            } else {
                return root.searchParent(value);
            }
        }

        // 找右子树中最小的节点,并删除（为删除节点准备）
        public int delRightTreeMin(Node node) {
            Node target = node;
            while (target.left != null){ // 最小节点一定在该节点的左子树里面
                 target = target.left;
            }

            del(target.value);
            return target.value; // 返回最小节点 该节点在删除左右都有子节点树的时候应该替代节点
        }

        // 删除节点
        public void del(int value) {
            if (root == null) { // 为空 不用删除
                return;
            } else {
                Node targetNode = search(value); // 找到要删除的节点
                if (targetNode == null) {
                    return;
                }

                // 二叉树只有一个节点 直接删除
                if (root.left == null && root.right == null) {
                    root = null;
                    return;
                }

                Node parent = searchParent(value);
                if (targetNode.left == null && targetNode.right == null) { // 要删除的节点是叶子节点
                    if (parent.left != null && parent.left.value == value) { // 判断这个叶子节点是左叶子还是右叶子
                        parent.left = null; // 是左叶子 左节点置空
                    } else if (parent.right != null && parent.right.value == value) {
                        parent.right = null;
                    }


                } else if (targetNode.left != null && targetNode.right != null) { // 要删除的节点有左节点和右节点两个子节点
                    int minVal = delRightTreeMin(targetNode.right); // 找到右子树中最小的并删除（或者左子树中最大的也可，两种方法，这里以第一种为例）
                    targetNode.value = minVal; // 将该节点变为右子树中最小的


                } else { // 要删除的节点只有一个子节点
                    if(targetNode.left != null) { // 要删除的节点有左子节点
                        if(parent != null){ // 排除要删除的是根节点
                            if(parent.left.value == value){
                                parent.left = targetNode.left;
                            }else {
                                parent.right = targetNode.left;
                            }
                        }
                    }else { // 要删除的节点有右子节点
                        if(parent != null){ // 排除要删除的是根节点
                            if(parent.left.value == value){
                                parent.left = targetNode.right;
                            }else {
                                parent.right = targetNode.right;
                            }
                        }
                    }
                }
            }


        }

        // 中序遍历
        public void infixOrder(Node root) {
            if (root != null) {
                infixOrder(root.left);
                System.out.print(root.value + " ");
                infixOrder(root.right);
            }
        }
    }


    /**
     * 创建节点
     */
    static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }

        // 插入节点 满足二叉排序树（根节点大于左节点且小于右节点）
        public void add(Node node) {
            if (node == null) {
                return;
            }
            if (node.value < this.value) { // 如果插入值小于当前值 则应该插在当前值的左边
                if (this.left == null) { // 如果当前值的左边为空，则直接插入
                    this.left = node;
                } else { // 否则 递归
                    this.left.add(node);
                }
            } else { //如果插入值大于等于当前值 则插在当前值的右边
                if (this.right == null) {
                    this.right = node;
                } else {
                    this.right.add(node);
                }
            }
        }

        // 查找要删除的节点
        public Node search(int value) {
            if (value == this.value) {
                return this;
            } else if (value < this.value) { // 如果查找的值小于当前节点，向左子树递归查找
                if (this.left == null) { // 如果左子节点为空，说明没有找到 返回null
                    return null;
                }
                return this.left.search(value);
            } else { // 如果查找的值小于当前节点，向右子树递归查找
                if (this.right == null) {
                    return null;
                }
                return this.right.search(value);
            }
        }

        // 查找要删除的节点的父节点
        public Node searchParent(int value) {
            if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
                return this;
            } else {
                if (value < this.value && this.left != null) { //左边递归查找
                    return this.left.searchParent(value);
                } else if (value >= this.value && this.right != null) { // 右边递归查找
                    return this.right.searchParent(value);
                } else { // 没找到
                    return null;
                }
            }
        }
    }
}