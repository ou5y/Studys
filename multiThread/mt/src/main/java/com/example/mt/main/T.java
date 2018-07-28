package com.example.mt.main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by baipan
 * Date: 2018-07-20
 */
public class T {

    public static void main(String[] args) {

        Lock lock = new ReentrantLock();

        Thread thread1 = new Thread(new T().new B("guo", lock));

        System.out.println(System.currentTimeMillis());
        thread1.start();

        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis()+"\t"+"begin interrupt");
        thread1.interrupt();
        System.out.println(System.currentTimeMillis()+"\t"+"end interrupt");

        ThreadLocal<String> local = new ThreadLocal<>();
        local.set("11");
        System.out.println(local.get());
        local.set("22");
        System.out.println(local.get());


    }

    class B implements Runnable {
        private String name;
        Lock lock;

        B(String name, Lock lock) {
            this.name = name;
            this.lock = lock;
        }

        @Override
        public void run() {
            try {
                System.out.println(System.currentTimeMillis()+"\t"+Thread.currentThread().getName()+"\tcome in");
                lock.lockInterruptibly();
                System.out.println(System.currentTimeMillis()+"\t"+Thread.currentThread().getName()+"\tlock");
                try {
                    System.out.println(System.currentTimeMillis()+"\t"+"I am " + name);
                    for (int i = 0; i < 1000000; i++){
                        System.out.print("");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("xxxx");
                } finally {
                    System.out.println(System.currentTimeMillis()+"\t"+Thread.currentThread().getName()+"\tunlock");
                    lock.unlock();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println(System.currentTimeMillis()+"\t"+"interrupted while polling!");
            }

        }
    }
}
