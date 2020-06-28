/*
* 用数组模拟循环队列
*/

import java.util.Scanner;

public class Queue {
    public static void main(String[] arg){
        // 测试
        ArrayQueue  queue = new ArrayQueue(4);
        char key = ' ';
        Scanner in = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("s(show）:显示队列");
            System.out.println("e(exit）:退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列中获取数字");
            key = in.next().charAt(0); //接收一个字符
            switch (key){
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入一个数字：");
                    int value = in.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                    System.out.println(queue.getQueue());
                    break;
                case 'e':
                    in.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
    }




    // 编写一个队列类
    static class ArrayQueue{
        private int maxSize;// 数组最大容量
        private int front;// 队列头
        private int rear;// 队列尾
        private int[] arr;// 该数组用于存放数据，模拟队列

        // 队列构造器
        public ArrayQueue(int arrMaxSize){
            maxSize = arrMaxSize;
            arr = new int[maxSize];
            front = 0;
            rear = 0;
        }

        // 判断队列是否满
        public boolean isFull(){
            return (rear +1)%maxSize == front;// 为了便于判断队列是空还是满 约定将队列容量空出一个
        }

        // 判断队列是否为空
        public boolean isEmpty(){
            return front == rear;
        }

        // 添加数据到队列
        public void addQueue(int n){
            if(isFull()){
                System.out.println("队列已满不能加入！");
                return;
            }
            arr[rear] = n;  // 添加数据都是添加到队尾
            rear = (rear + 1)%maxSize; // rear 后移一位
        }

        // 获取队列的数据 出队列
        public int getQueue(){
            if(isEmpty()){
                throw new RuntimeException("队列空，无法取数据");
            }
            int res = arr[front];
            front = (front + 1)%maxSize;
            return res;

        }

        // 显示所有数据
        public void showQueue(){
            if(isEmpty())
                throw new RuntimeException("队列空");
            for(int i=front;i<front+size();i++){
                System.out.println(arr[i%maxSize]);
            }
        }

        // 求出当前队列有效数据的个数
        public int size(){
            return (rear + maxSize - front)%maxSize;
        }

    }
}
