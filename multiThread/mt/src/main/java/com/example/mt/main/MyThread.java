package com.example.mt.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyThread extends Thread{

    private SimpleDateFormat sd;
    private String ds;

    public MyThread(SimpleDateFormat sd, String ds){
        this.sd = sd;
        this.ds = ds;
    }

    public void run(){
//        this.setUncaughtExceptionHandler((t, e) -> {
//            System.out.println(t.getName());
//            e.printStackTrace();
//        });
//        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
//            System.out.println(t.getName());
//            e.printStackTrace();
//        });
        try {
//            synchronized (this){
                Date date = sd.parse(ds);
                String nds = sd.format(date);

                if (!ds.equalsIgnoreCase(nds)){
                    System.out.println(String.format("不一样%s和%s", ds, nds));
                }
//            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
