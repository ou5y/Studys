package com.example.mt.main;

public class MyObject {
    private static MyObject myobject;

    private MyObject() {}

    public static MyObject getInstance(){
        try {
            if (myobject == null){
                //模拟在创建对象之前做一些准备性的工作
                Thread.sleep(3000);
                //myobject虽然是static的，但是多线程并发的时候会出现线程安全问题，保证单例就这样搞
                synchronized(MyObject.class){
                    if (myobject == null){
                        myobject = new MyObject();
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
        return myobject;
    }
}