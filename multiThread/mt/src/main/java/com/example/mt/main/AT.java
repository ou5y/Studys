package com.example.mt.main;


public class AT implements Runnable{


    @Override
    public void run() {
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(B.getInstance().hashCode());
//        try {
//            system.out.println("at执行锁定");
//            tools.getinstacnce().lock.lock();
//            tools.getinstacnce().condition.await();
//            system.out.println("at执行完毕");
//        }catch (exception e){
//            e.printstacktrace();
//        }finally {
//            system.out.println("at解锁");
//            tools.getinstacnce().lock.unlock();
//        }
    }
}
