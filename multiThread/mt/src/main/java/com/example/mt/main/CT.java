package com.example.mt.main;


public class CT{
    public static void main(String[] args){
        B a = B.getInstance();
        System.out.println(a.hashCode());
        new Thread(new AT()).start();
        new Thread(new BT()).start();
    }

    private static class Holder{
        private static  final CT ct = new CT();
    }

}
