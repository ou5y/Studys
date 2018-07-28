package com.example.mt.main;


import java.util.Map;

public class B {

    private B(){}
    //延迟加载（懒汉模式）
    private static B a;
    public static B getInstance(){
        if (a == null)
            a = new B();
        return a;
    }

//    Thread thread1 = new Thread(new Runnable() {
//        @Override
//        public void run() {
//            System.out.println(getInstance().hashCode());
//        }
//    });

//    Thread thread2 = new Thread(new Runnable() {
//        @Override
//        public void run() {
//            System.out.println(getInstance().hashCode());
//        }
//    });



    public static void main(String[] args) throws Exception {

//        Map<String, Object> map = IppHelper.resolverReceiveMessage(IppHelper.constructionMessage("top",(byte) 4,"詹三","美国","Welcome you 三娃子".getBytes()));
//        System.out.println(map.toString());
    }

}
