package com.datastructure.queue;

import java.util.Scanner;

public class CircleArrayQueueDemo {
    public static void main(String[] args) {

        System.out.println("测试数组模拟环形队列的案列");                //测试
        CircleArray circleArray = new CircleArray(1);        //创建一个环形队列,设置为4，其队列的有效数据最大为3
        char key = ' ';                                                  //接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add) :添加数据到队列");
            System.out.println("g(get) :从队列取出数据");
            System.out.println("h(head):查看队列的头数据");
            key = scanner.next().charAt(0);                              //接收一个字符
            switch (key) {
                case 's':
                    try {
                        circleArray.showQueue();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'a':
                    System.out.println("输入一个数：");
                    int value = scanner.nextInt();
                    circleArray.addQueue(value);
                    break;
                case 'g':
                    try {
                        int result = circleArray.getQueue();
                        System.out.println("取出的数据是：" + result);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int result = circleArray.headQueue();
                        System.out.println("队列的头数据是：" + result);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }

        System.out.println("程序已退出");

    }
}

class CircleArray {
    private int maxSize;  //数组的最大容量
    private int front;    //指向队列的第一个元素,初始值为0
    private int rear;     //指向队列的最后一个元素的后一个位置,初始值为0
    private int[] arr;    //用于存放数据，模拟队列

    public CircleArray(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[arrMaxSize];
    }

    //判断队列是否满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    //判断队列是否空
    public boolean isEmpty() {
        return front == rear;
    }

    //添加数据到对列
    public void addQueue(int number) {
        //判断队列是否满
        if (isFull()) {
            System.out.println("队列已满");
            return;
        }
        arr[rear] = number;                  //直接将数据加入
        rear = (rear + 1) % maxSize;         //将rear后移，这里必须考虑取模
    }

    //获取队列的数据，出队列
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空，不能取数据");
        }
        int value = arr[front];               //先把值保存在临时变量里面
        front = (front + 1) % maxSize;        //将front后移，必须考虑取模
        return value;                         //将临时保存的变量返回
    }

    //显示所有的数据
    public void showQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空，没有数据");
        }
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    //求出当前队列的有效数据的个数，否则无法遍历
    public int size() {
        return (rear - front + maxSize) % maxSize;
    }

    //显示队列的头数据，注意不是取出数据
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空，没有数据");
        }
        return arr[front];
    }
}