package com.example.mt.main;


public class BT implements Runnable{

    @Override
    public void run() {
        System.out.println(B.getInstance().hashCode());
//        try {
//            System.out.println("BT通知锁定");
//            Tools.getInstacnce().lock.lock();
//            Tools.getInstacnce().condition.signal();
//            System.out.println("BT通知完毕");
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            System.out.println("BT解锁");
//            Tools.getInstacnce().lock.unlock();
//        }
    }
}
