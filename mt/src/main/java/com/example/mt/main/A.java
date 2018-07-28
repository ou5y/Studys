package com.example.mt.main;

public class A {

    private A(){}
    //立即加载（饿汉模式）
    private static A a = new A();
    public static A getInstance(){
        return a;
    }

    Thread thread1 = new Thread(() -> System.out.println(getInstance().hashCode()));

    Thread thread2 = new Thread(() -> System.out.println(getInstance().hashCode()));


    public static void main(String[] args){
        A a = getInstance();
        System.out.println(a.hashCode());
        a.thread1.start();
        a.thread2.start();
    }



}
