package com.datastructure.queue;

public class ArrQueueDemo {
    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(3);
    }
}

//使用数组模拟队列 编写一个ArrayQueue类
class ArrayQueue{
    private int maxSize;  //数组的最大容量
    private int front;    //队列头
    private int rear;     //队列尾
    private int[] arr;    //用于存放数据，模拟队列

    //创建队列的构造器
    public ArrayQueue(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[arrMaxSize];
        front = -1;  //指向队列头部，分析出front是指向队列头的前面一个位置
        rear = -1;   //指向队列尾部，指向队列尾的数据(即就是队列最后一个数据)
    }

    //判断队列是否满
    public boolean isFull(){
        return maxSize - 1 == rear;
    }

    //判断队列是否空
    public boolean isEmpty(){
        return front == rear;
    }

    //添加数据到对列
    public void addQueue(int number){
        //判断队列是否满
        if(isFull()){
            System.out.println("队列已满");
            return;
        }
        rear++;  //让rear后移
        arr[rear] = number;
    }

    //获取队列的数据，出队列
    public int getQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列空，不能取数据");
        }
        front++;
        return arr[front];
    }

    //显示队列的所有数据
    public void showQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列空，没有数据");
        }
        for(int i=0;i<arr.length;i++){
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }

    //显示队列的头数据，注意不是取出数据
    public int headQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列空，没有数据");
        }
        return arr[front + 1];
    }

}
