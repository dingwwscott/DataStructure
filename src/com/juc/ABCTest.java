package com.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ABCTest {
    public static void main(String[] args) {
        ThreadCommunication tc = new ThreadCommunication();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1;i<11;i++){
                    tc.loopA();
                }
            }
        },"A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1;i<11;i++){
                    tc.loopB();
                }
            }
        },"" +
                "B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1;i<11;i++){
                    tc.loopC();
                }
            }
        },"C").start();
    }
}

class ThreadCommunication{
    private int number = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void loopA(){
        lock.lock();
        try {
            if (number != 1){
                try {
                    condition1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print(Thread.currentThread().getName());
            number=2;
            condition2.signal();
        }finally {
            lock.unlock();
        }
    }

    public void loopB(){
        lock.lock();
        try {
            if (number != 2){
                try {
                    condition2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print(Thread.currentThread().getName());
            number=3;
            condition3.signal();
        }finally {
            lock.unlock();
        }
    }

    public void loopC(){
        lock.lock();
        try {
            if (number != 3){
                try {
                    condition3.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print(Thread.currentThread().getName());
            number=1;
            condition1.signal();
        }finally {
            lock.unlock();
        }
    }
}