package com.example.mt.main;

public class Run2 {
    public static void main(String[] args){
        Thread t1 = new Thread(() -> {
            System.out.println(getInstance().hashCode());
            System.out.println("hello");
        });
        Thread t2 = new Thread(() -> System.out.println(getInstance().hashCode()));
        t1.start();
        t2.start();
    }

    private Run2(){}
    private static Run2 run2 = null;

    //有static是只有第一次访问类的时候加载，没有是每访问一次类加载一次
    static {
        run2 = new Run2();
    }

    public static Run2 getInstance(){
        return run2;
    }
}
