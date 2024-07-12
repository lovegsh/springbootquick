package com.gsh.springbootquick.algorithm.a;

import java.util.Scanner;

public class ArrayQueueDemo {

    public static void main(String[] args) {

        ArrayQueue arrayQueue = new ArrayQueue(4);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列中取出数据");
            System.out.println("h(head):查看队列头的数据");
            key = scanner.next().charAt(0);
            switch (key) {
            case 's':
                arrayQueue.showQueue();
                break;
            case 'a':
                if (arrayQueue.isFull()) {
                    System.out.println("队列满了~~不能添加数据");
                    break;
                }
                System.out.println("请输入一个数");
                int value = scanner.nextInt();
                arrayQueue.addQueue(value);
                break;
            case 'g':
                try {
                    int res = arrayQueue.getQueue();
                    System.out.println("取出的数据是：" + res);
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
                break;
            case 'h':
                try {
                    int res = arrayQueue.headQueue();
                    System.out.println("队列头的数据是：" + res);
                } catch (Exception e) {
                    System.err.println(e.getMessage());
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
        System.out.println("-----程序退出-----");
    }
}

class ArrayQueue {
    private int maxSize;// 表示数组的最大容量
    private int front;// 队列头
    private int rear;// 队列尾
    private int[] arr;// 存放数组

    public ArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = 0;// 指向队列头的前一个位置
        rear = 0;// 指向队列最后一个数据
    }

    public boolean isFull() {
        return ((rear + 1) % maxSize) == front;
    }

    public boolean isEmpty() {
        return rear == front;
    }

    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列满，不能添加数据~~");
            return;
        }
        arr[rear] = n;
        rear = (rear + 1) % maxSize;
    }

    // 获取队列的数据，出队列
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空，不能取数据");
        }
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空");
            return;
        }
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    // 显示队列的头数据
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        return arr[front];
    }

    // 求当前队列有效数据个数
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }
}
