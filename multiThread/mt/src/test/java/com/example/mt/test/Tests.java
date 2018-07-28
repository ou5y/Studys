package com.example.mt.test;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by baipan
 * Date: 2018-07-19
 */
public class Tests {

    private int i = 10;
    private final Object object = new Object();

    public static void main(String[] args) throws IOException {
        Tests test = new Tests();
        MyThread thread1 = test.new MyThread();
        MyThread thread2 = test.new MyThread();
        thread1.start();
        thread2.start();
    }


    class MyThread extends Thread{
        @Override
        public void run() {
            synchronized (object) {
                i++;
                System.out.println(new Date().toLocaleString()+"\ti:"+i);
                try {
                    System.out.println(new Date().toLocaleString()+"\t线程"+Thread.currentThread().getName()+"进入睡眠状态");
                    sleep(10000);
                } catch (InterruptedException e) {
                }
                System.out.println(new Date().toLocaleString()+"\t线程"+Thread.currentThread().getName()+"睡眠结束");
                i++;
                System.out.println(new Date().toLocaleString()+"\ti:"+i);
            }
        }
    }
}
