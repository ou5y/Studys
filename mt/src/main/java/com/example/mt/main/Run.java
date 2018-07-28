package com.example.mt.main;

import java.text.SimpleDateFormat;

public class Run {

    public static void main(String[] args){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String[] strs = new String[]{"2010-10-01","2010-10-02","2010-10-03","2010-10-04","2010-10-05",
                "2010-10-06","2010-10-07","2010-10-08","2010-10-09","2010-10-10"};
        MyThread ts[] = new MyThread[strs.length];
        for (int i=0; i<strs.length; i++){
            ts[i] = new MyThread(sf, strs[i]);
//            ts[i] = new MyThread(new SimpleDateFormat("yyyy-MM-dd"), strs[i]);
        }
        for (MyThread t : ts) {
            t.start();
        }
    }



}
