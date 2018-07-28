package com.example.mt.main;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by baipan
 * Date: 2018-07-20
 */
public class Q1 {

    public static void main(String[] args) {
        Q1 q1 = new Q1();
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        try {
            condition.await();
        }catch (Exception e){
            e.printStackTrace();
        }
//        ExecutorService executor = Executors.newCachedThreadPool();
//        Task task = q1.new Task();
//        Task2 task2 = q1.new Task2();
//        Future<Integer> result = executor.submit(task);
//        Future<?> result2 = executor.submit(task2);
//        executor.shutdown();
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e1) {
//            e1.printStackTrace();
//        }
//
//        System.out.println("主线程在执行任务");
//
//        try {
//            System.out.println("task运行结果"+result2.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("所有任务执行完毕");
    }


    class Task2 implements Runnable {
        @Override
        public void run() {
            System.out.println("子线程在进行计算");
            int sum = 0;
            for(int i=0;i<100;i++)
                sum += i;
        }
    }


    class Task implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            System.out.println("子线程在进行计算");
            Thread.sleep(3000);
            int sum = 0;
            for(int i=0;i<100;i++)
                sum += i;
            return sum;
        }
    }
}