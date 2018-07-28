package com.example.mt.main;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by baipan
 * Date: 2018-07-20
 */
public class DateTests {

    public static void main(String[] args) throws Exception{

        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>(20);
        System.out.println(queue.size());
        queue.offer("1");
        System.out.println(queue.size());
        queue.peek();
        System.out.println(queue.size());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY年MM月dd日 HH:mm:ss");
        long cha = 24 * 3600 * 1000;
        System.out.println("****************************11\t" + simpleDateFormat.format(new Date(System.currentTimeMillis() - cha)));
        Thread.sleep(10);
        cha = 30L * 24L * 3600L * 1000L;
        System.out.println("max:"+Integer.MAX_VALUE);
        System.out.println("cha:"+cha);
        System.out.println("****************************22\t" + simpleDateFormat.format(new Date(System.currentTimeMillis() - cha)));
    }

}
